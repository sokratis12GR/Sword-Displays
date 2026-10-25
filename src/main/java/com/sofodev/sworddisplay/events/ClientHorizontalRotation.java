package com.sofodev.sworddisplay.events;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import com.sofodev.sworddisplay.blocks.SwordDisplayBlock;
import com.sofodev.sworddisplay.blocks.RackDisplayBlock;
import com.sofodev.sworddisplay.blocks.RackCaseBlock;
import com.sofodev.sworddisplay.blocks.RackWallBlock;
import com.sofodev.sworddisplay.network.HorizontalRotatePayload;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.lwjgl.glfw.GLFW;

import static net.neoforged.api.distmarker.Dist.CLIENT;

@EventBusSubscriber(modid = "sworddisplay", value = CLIENT)
public final class ClientHorizontalRotation {
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getLevel().isClientSide() || event.getHand() != InteractionHand.MAIN_HAND) return;
        if (!(event.getLevel().getBlockState(event.getPos()).getBlock() instanceof SwordDisplayBlock)) return;
        if (!event.getItemStack().isEmpty() || event.getEntity().isShiftKeyDown()) return;
        Window window = Minecraft.getInstance().getWindow();
        if (!InputConstants.isKeyDown(window, GLFW.GLFW_KEY_LEFT_CONTROL)
            && !InputConstants.isKeyDown(window, GLFW.GLFW_KEY_RIGHT_CONTROL)) return;
        var block = event.getLevel().getBlockState(event.getPos());
        int slot = (block.getBlock() instanceof RackDisplayBlock
                || block.getBlock() instanceof RackCaseBlock
                || block.getBlock() instanceof RackWallBlock)
                ? RackDisplayBlock.slot(block, event.getPos(), event.getHitVec()) : 0;
        ClientPacketDistributor.sendToServer(new HorizontalRotatePayload(event.getPos(), slot));
        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }
}
