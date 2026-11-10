package com.sofodev.sworddisplay;

import com.sofodev.sworddisplay.network.HorizontalRotatePayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import com.sofodev.sworddisplay.client.ClientConfigScreen;
import com.sofodev.sworddisplay.config.SDConfig;
import net.neoforged.fml.common.Mod;
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
    private static final int MIN_JAVA_VERSION = 21;

    public SwordDisplay(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, SDConfig.SPEC);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientConfigScreen.register(modContainer);
        }
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        modEventBus.addListener(HorizontalRotatePayload::register);
    }
}
