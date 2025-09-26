package com.sofodev.sworddisplay.events;

import com.sofodev.sworddisplay.blocks.TESRSwordDisplay;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static com.sofodev.sworddisplay.registry.ModBlocks.*;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void doClientStuff(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(SWORD_CASE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(WOODEN_SWORD_CASE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(DARK_OAK_SWORD_CASE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BIRCH_SWORD_CASE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ACACIA_SWORD_CASE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(JUNGLE_SWORD_CASE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(SPRUCE_SWORD_CASE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(PRISMARINE_SWORD_CASE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(IRON_SWORD_CASE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(GOLDEN_SWORD_CASE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(DIAMOND_SWORD_CASE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(EMERALD_SWORD_CASE.get(), RenderType.cutout());
            BlockEntityRenderers.register(SWORD_DISPLAY_TYPE.get(), TESRSwordDisplay::new);
        });
    }
}
