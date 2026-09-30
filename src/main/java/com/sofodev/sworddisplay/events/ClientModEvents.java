package com.sofodev.sworddisplay.events;

import com.sofodev.sworddisplay.blocks.TESRSwordDisplay;
import net.minecraftforge.client.event.EntityRenderersEvent;

import static com.sofodev.sworddisplay.registry.ModBlocks.SWORD_DISPLAY_TYPE;

public final class ClientModEvents {
    private ClientModEvents() {}

    public static void register() {
        EntityRenderersEvent.RegisterRenderers.BUS.addListener(ClientModEvents::registerRenderers);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(SWORD_DISPLAY_TYPE.get(), TESRSwordDisplay::new);
    }
}
