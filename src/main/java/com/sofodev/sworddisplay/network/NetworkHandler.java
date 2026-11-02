package com.sofodev.sworddisplay.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;

public final class NetworkHandler {
    private NetworkHandler() {}

    public static final Channel<CustomPacketPayload> CHANNEL = ChannelBuilder
            .named(Identifier.fromNamespaceAndPath(MODID, "main"))
            .networkProtocolVersion(1)
            .payloadChannel()
            .play()
            .serverbound()
            .add(HorizontalRotatePayload.TYPE, HorizontalRotatePayload.STREAM_CODEC, HorizontalRotatePayload::handle)
            .build();

    // Referencing CHANNEL forces the static initializer above to run and register the channel.
    public static void register() {}

    public static void sendRotate(BlockPos pos, int slot) {
        CHANNEL.send(new HorizontalRotatePayload(pos, slot), PacketDistributor.SERVER.noArg());
    }
}
