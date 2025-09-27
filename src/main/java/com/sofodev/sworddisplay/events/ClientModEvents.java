package com.sofodev.sworddisplay.events;

import com.sofodev.sworddisplay.blocks.TESRSwordDisplay;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static com.sofodev.sworddisplay.registry.ModBlocks.SWORD_DISPLAY_TYPE;
import static com.sofodev.sworddisplay.registry.ModBlocks.registryHelper;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void doClientStuff(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            registryHelper.forEach(ro ->
                    ItemBlockRenderTypes.setRenderLayer(ro.getCaseBlock().get(), RenderType.cutout())
            );

            BlockEntityRenderers.register(SWORD_DISPLAY_TYPE.get(), TESRSwordDisplay::new);
        });
    }
}
