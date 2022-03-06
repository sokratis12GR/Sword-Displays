package com.sofodev.sworddisplay.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SwordCaseBlock extends SwordDisplayBlock {

    protected static final VoxelShape FULL_MODEL = Shapes.or(box(0, 2, 0, 16, 21, 16), VOXEL);

    public SwordCaseBlock(Properties type) {
        super(type.dynamicShape());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return FULL_MODEL;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return FULL_MODEL;
    }
}