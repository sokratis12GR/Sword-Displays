package com.sofodev.sworddisplay.events;

import com.sofodev.sworddisplay.blocks.TESRSwordDisplay;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static com.sofodev.sworddisplay.registry.ModBlocks.SWORD_DISPLAY_TYPE;
import static net.neoforged.api.distmarker.Dist.CLIENT;

@EventBusSubscriber(modid = MODID, value = CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void doClientStuff(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BlockEntityRenderers.register(SWORD_DISPLAY_TYPE.get(), TESRSwordDisplay::new);
        });
    }
}
