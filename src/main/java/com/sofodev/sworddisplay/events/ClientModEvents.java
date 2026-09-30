package com.sofodev.sworddisplay.events;

import com.sofodev.sworddisplay.blocks.TESRSwordDisplay;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static com.sofodev.sworddisplay.registry.ModBlocks.SWORD_DISPLAY_TYPE;
import static net.neoforged.api.distmarker.Dist.CLIENT;

@EventBusSubscriber(modid = MODID, value = CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(SWORD_DISPLAY_TYPE.get(), TESRSwordDisplay::new);
    }
}
