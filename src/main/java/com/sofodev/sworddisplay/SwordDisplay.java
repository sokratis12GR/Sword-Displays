package com.sofodev.sworddisplay;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import com.sofodev.sworddisplay.data.client.SDClientData;
import com.sofodev.sworddisplay.data.server.SDServerData;
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

    public SwordDisplay(IEventBus modEventBus) {
        modEventBus.addListener(SDClientData::gatherData);
        modEventBus.addListener(SDServerData::gatherData);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
    }
}
