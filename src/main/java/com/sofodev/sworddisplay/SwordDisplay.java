package com.sofodev.sworddisplay;

import com.sofodev.sworddisplay.data.client.SDClientData;
import com.sofodev.sworddisplay.data.server.SDServerData;
import com.sofodev.sworddisplay.events.ClientModEvents;
import com.sofodev.sworddisplay.events.WorldEvents;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.sofodev.sworddisplay.registry.ModBlocks.BLOCKS;
import static com.sofodev.sworddisplay.registry.ModBlocks.TILE_ENTITIES;
import static com.sofodev.sworddisplay.registry.ModCreativeTabs.CREATIVE_MODE_TABS;
import static com.sofodev.sworddisplay.registry.ModItems.ITEMS;

@Mod(SwordDisplay.MODID)
public class SwordDisplay {
    public static final String MODID = "sworddisplay";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public SwordDisplay(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();

        GatherDataEvent.getBus(modBusGroup).addListener(event -> {
            SDClientData.gatherData(event);
            SDServerData.gatherData(event);
        });
        ClientModEvents.register();
        WorldEvents.register();

        BLOCKS.register(modBusGroup);
        ITEMS.register(modBusGroup);
        CREATIVE_MODE_TABS.register(modBusGroup);
        TILE_ENTITIES.register(modBusGroup);
    }
}
