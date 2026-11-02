package com.sofodev.sworddisplay;

import com.sofodev.sworddisplay.client.ClientConfigScreen;
import com.sofodev.sworddisplay.config.SDConfig;
import com.sofodev.sworddisplay.data.client.SDClientData;
import com.sofodev.sworddisplay.data.server.SDServerData;
import com.sofodev.sworddisplay.events.ClientModEvents;
import com.sofodev.sworddisplay.events.WorldEvents;
import com.sofodev.sworddisplay.network.NetworkHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
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

        context.registerConfig(ModConfig.Type.COMMON, SDConfig.SPEC);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientConfigScreen.register(context.getContainer());
        }

        GatherDataEvent.getBus(modBusGroup).addListener(event -> {
            SDClientData.gatherData(event);
            SDServerData.gatherData(event);
        });
        ClientModEvents.register();
        WorldEvents.register();
        NetworkHandler.register();

        BLOCKS.register(modBusGroup);
        ITEMS.register(modBusGroup);
        CREATIVE_MODE_TABS.register(modBusGroup);
        TILE_ENTITIES.register(modBusGroup);
    }
}
