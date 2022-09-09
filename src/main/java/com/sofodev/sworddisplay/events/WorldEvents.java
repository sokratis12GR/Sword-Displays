package com.sofodev.sworddisplay.events;

import com.mojang.authlib.GameProfile;
import com.sofodev.sworddisplay.blocks.SwordDisplayTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class WorldEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockPos pos = event.getPos();
        Level world = (Level) event.getLevel();
        BlockEntity te = world.getBlockEntity(pos);
        Player player = event.getPlayer();
        GameProfile profile = player.getGameProfile();
        UUID playerUUID = profile.getId();
        if (!world.isClientSide() && te instanceof SwordDisplayTile displayTile) {
            boolean isTheOwner = playerUUID.equals(displayTile.getOwner());
            ItemStack sword = displayTile.getSword();
            if (!sword.isEmpty() && !isTheOwner && displayTile.getOwner() != null && !player.getAbilities().instabuild) {
                event.setCanceled(true);
            }
        }
    }

}
