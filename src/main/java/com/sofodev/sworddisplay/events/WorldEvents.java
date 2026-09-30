package com.sofodev.sworddisplay.events;

import com.mojang.authlib.GameProfile;
import com.sofodev.sworddisplay.blocks.SwordDisplayTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.Result;
import net.minecraftforge.event.level.BlockEvent;

import java.util.UUID;

;

public final class WorldEvents {
    private WorldEvents() {
    }

    public static void register() {
        BlockEvent.BreakEvent.BUS.addListener(WorldEvents::onBlockBreak);
    }

    private static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockPos pos = event.getPos();
        Level world = (Level) event.getLevel();
        BlockEntity te = world.getBlockEntity(pos);
        Player player = event.getPlayer();
        GameProfile profile = player.getGameProfile();
        UUID playerUUID = profile.id();
        if (!world.isClientSide() && te instanceof SwordDisplayTile displayTile) {
            boolean isTheOwner = playerUUID.equals(displayTile.getOwner());
            ItemStack sword = displayTile.getSword();
            if (!sword.isEmpty() && !isTheOwner && displayTile.getOwner() != null && !player.getAbilities().instabuild) {
                event.setResult(Result.DENY);
            }
        }
    }
}
