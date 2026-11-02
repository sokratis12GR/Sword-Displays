package com.sofodev.sworddisplay.events;

import com.sofodev.sworddisplay.blocks.RackCaseBlock;
import com.sofodev.sworddisplay.blocks.RackDisplayBlock;
import com.sofodev.sworddisplay.blocks.RackWallBlock;
import com.sofodev.sworddisplay.blocks.SwordDisplayBlock;
import com.sofodev.sworddisplay.network.HorizontalRotatePayload;
import com.sofodev.sworddisplay.network.NetworkHandler;
import com.sofodev.thedragonlib.util.InfoHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.network.PacketDistributor;

public final class ClientHorizontalRotation {
    private ClientHorizontalRotation() {}

    public static void register() {
        PlayerInteractEvent.RightClickBlock.BUS.addListener(ClientHorizontalRotation::onRightClick);
    }

    // Returning true cancels the event, per CancellableEventBus#addListener(Predicate).
    private static boolean onRightClick(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getLevel().isClientSide() || event.getHand() != InteractionHand.MAIN_HAND) return false;
        BlockState block = event.getLevel().getBlockState(event.getPos());
        if (!(block.getBlock() instanceof SwordDisplayBlock)) return false;
        if (!event.getItemStack().isEmpty() || event.getEntity().isShiftKeyDown()) return false;
        if (!InfoHelper.isCtrlKeyDown()) return false;

        int slot = (block.getBlock() instanceof RackDisplayBlock
                || block.getBlock() instanceof RackCaseBlock
                || block.getBlock() instanceof RackWallBlock)
                ? RackDisplayBlock.slot(block, event.getPos(), event.getHitVec()) : 0;

        NetworkHandler.CHANNEL.send(new HorizontalRotatePayload(event.getPos(), slot), PacketDistributor.SERVER.noArg());
        event.setCancellationResult(InteractionResult.SUCCESS);
        return true;
    }
}
