package com.sofodev.sworddisplay.events;

import com.mojang.authlib.GameProfile;
import com.sofodev.sworddisplay.blocks.SwordDisplayTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class WorldEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockPos pos = event.getPos();
        World world = (World) event.getWorld();
        TileEntity te = world.getTileEntity(pos);
        PlayerEntity player = event.getPlayer();
        GameProfile profile = player.getGameProfile();
        UUID playerUUID = profile.getId();
        if (!world.isRemote && te instanceof SwordDisplayTile) {
            SwordDisplayTile displayTile = (SwordDisplayTile) te;
            boolean isTheOwner = displayTile.getOwner() == playerUUID;
            ItemStack sword = displayTile.getSword();
            if (!sword.isEmpty() && !isTheOwner) {
                event.setCanceled(true);
            }
        }
    }

}
