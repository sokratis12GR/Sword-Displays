package com.sofodev.sworddisplay.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/** A three-position weapon rack. Each slot owns its own persisted ItemStack. */
public class RackDisplayBlock extends SwordDisplayBlock {
    public RackDisplayBlock(Properties properties) { super(properties); }

    @Override
    public net.minecraft.world.phys.shapes.VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        return RackModelShapes.display(state.getValue(FACING));
    }

    @Override
    public net.minecraft.world.phys.shapes.VoxelShape getCollisionShape(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        return RackModelShapes.display(state.getValue(FACING));
    }

    public static int slot(BlockState state, BlockPos pos, BlockHitResult hit) {
        double x = hit.getLocation().x - pos.getX();
        double z = hit.getLocation().z - pos.getZ();
        Direction facing = state.getValue(FACING);
        double horizontal = switch (facing) {
            case SOUTH -> x;
            case NORTH -> 1.0 - x;
            case EAST -> z;
            case WEST -> 1.0 - z;
            default -> x;
        };
        return Math.min(2, Math.max(0, (int)(horizontal * 3)));
    }

    @Override
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return interact(stack, state, world, pos, player, hand, hit);
    }

    static InteractionResult interact(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
        if (!(world.getBlockEntity(pos) instanceof SwordDisplayTile tile)) return InteractionResult.PASS;
        if (world.isClientSide()) return InteractionResult.SUCCESS;
        int index = slot(state, pos, hit);
        boolean owner = tile.getOwner() != null && tile.getOwner().equals(player.getUUID());
        if (player.isShiftKeyDown() && stack.isEmpty()) {
            if (owner && !tile.getWeapon(index).isEmpty()) {
                player.drop(tile.getWeapon(index).copy(), false);
                tile.setWeapon(index, ItemStack.EMPTY);
            }
            return InteractionResult.SUCCESS;
        }
        boolean weapon = stack.is(SWORDS) || stack.is(ItemTags.SWORDS) || stack.is(ItemTags.WEAPON_ENCHANTABLE) || stack.is(ItemTags.AXES);
        if (weapon && tile.getWeapon(index).isEmpty() && (tile.getOwner() == null || owner)) {
            tile.setWeapon(index, stack.copyWithCount(1));
            tile.setOwner(player.getUUID());
            stack.shrink(1);
            return InteractionResult.SUCCESS;
        }
        if (stack.isEmpty() && owner && !tile.getWeapon(index).isEmpty()) {
            if (!tile.consumeHorizontalClick(player.getUUID())) tile.rotateRackTilt(index);
        }
        return InteractionResult.SUCCESS;
    }
}
