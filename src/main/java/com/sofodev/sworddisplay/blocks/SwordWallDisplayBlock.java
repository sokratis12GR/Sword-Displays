package com.sofodev.sworddisplay.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class SwordWallDisplayBlock extends SwordDisplayBlock {

    protected static final VoxelShape SOUTH_AABB = Stream.of(
            Block.box(4, 4, 14, 12, 12, 16),
            Block.box(10, 12, 14, 12, 14, 16),
            Block.box(7, 12, 14, 9, 14, 16),
            Block.box(7, 2, 14, 9, 4, 16),
            Block.box(4, 2, 14, 6, 4, 16),
            Block.box(12, 4, 14, 14, 6, 16),
            Block.box(12, 7, 14, 14, 9, 16),
            Block.box(2, 10, 14, 4, 12, 16),
            Block.box(2, 7, 14, 4, 9, 16),
            Block.box(4, 12, 14, 6, 14, 16),
            Block.box(12, 10, 14, 14, 12, 16),
            Block.box(2, 4, 14, 4, 6, 16),
            Block.box(10, 2, 14, 12, 4, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();
    protected static final VoxelShape NORTH_AABB = Stream.of(
            Block.box(4, 4, 0, 12, 12, 2),
            Block.box(10, 12, 0, 12, 14, 2),
            Block.box(7, 12, 0, 9, 14, 2),
            Block.box(7, 2, 0, 9, 4, 2),
            Block.box(4, 2, 0, 6, 4, 2),
            Block.box(12, 4, 0, 14, 6, 2),
            Block.box(12, 7, 0, 14, 9, 2),
            Block.box(2, 10, 0, 4, 12, 2),
            Block.box(2, 7, 0, 4, 9, 2),
            Block.box(4, 12, 0, 6, 14, 2),
            Block.box(12, 10, 0, 14, 12, 2),
            Block.box(2, 4, 0, 4, 6, 2),
            Block.box(10, 2, 0, 12, 4, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();
    protected static final VoxelShape EAST_AABB = Stream.of(
            Block.box(14, 4, 4, 16, 12, 12),
            Block.box(14, 12, 4, 16, 14, 6),
            Block.box(14, 12, 7, 16, 14, 9),
            Block.box(14, 2, 7, 16, 4, 9),
            Block.box(14, 2, 10, 16, 4, 12),
            Block.box(14, 4, 2, 16, 6, 4),
            Block.box(14, 7, 2, 16, 9, 4),
            Block.box(14, 10, 12, 16, 12, 14),
            Block.box(14, 7, 12, 16, 9, 14),
            Block.box(14, 12, 10, 16, 14, 12),
            Block.box(14, 10, 2, 16, 12, 4),
            Block.box(14, 4, 12, 16, 6, 14),
            Block.box(14, 2, 4, 16, 4, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();
    protected static final VoxelShape WEST_AABB = Stream.of(
            Block.box(0, 4, 4, 2, 12, 12),
            Block.box(0, 12, 4, 2, 14, 6),
            Block.box(0, 12, 7, 2, 14, 9),
            Block.box(0, 2, 7, 2, 4, 9),
            Block.box(0, 2, 10, 2, 4, 12),
            Block.box(0, 4, 2, 2, 6, 4),
            Block.box(0, 7, 2, 2, 9, 4),
            Block.box(0, 10, 12, 2, 12, 14),
            Block.box(0, 7, 12, 2, 9, 14),
            Block.box(0, 12, 10, 2, 14, 12),
            Block.box(0, 10, 2, 2, 12, 4),
            Block.box(0, 4, 12, 2, 6, 14),
            Block.box(0, 2, 4, 2, 4, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();
    public static final BooleanProperty IS_WALL_DISPLAY = BooleanProperty.create("is_wall_display");


    public SwordWallDisplayBlock(Properties type) {
        super(type.dynamicShape());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(IS_REVERSE, Boolean.FALSE)
                .setValue(IS_WALL_DISPLAY, Boolean.TRUE));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            case EAST -> EAST_AABB;
            default -> NORTH_AABB;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            case EAST -> EAST_AABB;
            default -> NORTH_AABB;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, IS_REVERSE,  IS_WALL_DISPLAY);
    }
}