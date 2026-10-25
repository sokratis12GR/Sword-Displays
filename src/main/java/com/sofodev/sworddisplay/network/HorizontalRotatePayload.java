package com.sofodev.sworddisplay.network;

import com.sofodev.sworddisplay.blocks.SwordDisplayTile;
import com.sofodev.sworddisplay.blocks.RackDisplayBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public record HorizontalRotatePayload(BlockPos pos, int slot) implements CustomPacketPayload {
    public static final Type<HorizontalRotatePayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath("sworddisplay", "horizontal_rotate"));
    public static final StreamCodec<RegistryFriendlyByteBuf, HorizontalRotatePayload> STREAM_CODEC = StreamCodec.of(
        (buffer, payload) -> { buffer.writeBlockPos(payload.pos()); buffer.writeVarInt(payload.slot()); },
        buffer -> new HorizontalRotatePayload(buffer.readBlockPos(), buffer.readVarInt())
    );

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(TYPE, STREAM_CODEC, HorizontalRotatePayload::handle);
    }

    private static void handle(HorizontalRotatePayload message, IPayloadContext context) {
        if (!(context.player() instanceof ServerPlayer player)) return;
        if (player.distanceToSqr(message.pos().getX() + .5, message.pos().getY() + .5, message.pos().getZ() + .5) > 36.0) return;
        if (!(player.level().getBlockEntity(message.pos()) instanceof SwordDisplayTile tile)) return;
        if ((tile.getWeapon(0).isEmpty() && tile.getWeapon(1).isEmpty() && tile.getWeapon(2).isEmpty()) || !player.getUUID().equals(tile.getOwner())) return;
        if (tile.isRack()) {
            if (message.slot() < 0 || message.slot() > 2 || tile.getWeapon(message.slot()).isEmpty()) return;
            tile.markHorizontalClick(player.getUUID());
            tile.rotateRackHorizontal(message.slot());
        } else {
            tile.markHorizontalClick(player.getUUID());
            tile.rotateHorizontally(1);
        }
    }
}
