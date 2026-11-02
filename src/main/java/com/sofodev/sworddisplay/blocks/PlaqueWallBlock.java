package com.sofodev.sworddisplay.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class PlaqueWallBlock extends SwordWallDisplayBlock {
    // Matches the five cuboids in wall_display_base_plaque.json.
    private static final double[][] PARTS = {
            {4, 3, 14, 12, 13, 16},
            {5, 13, 14, 11, 15, 16},
            {5, 1, 14, 11, 3, 16},
            {3, 5, 14, 4, 11, 16},
            {12, 5, 14, 13, 11, 16}
    };

    private static final VoxelShape SOUTH = makeShape(Direction.SOUTH);
    private static final VoxelShape NORTH = makeShape(Direction.NORTH);
    private static final VoxelShape EAST = makeShape(Direction.EAST);
    private static final VoxelShape WEST = makeShape(Direction.WEST);

    public PlaqueWallBlock(Properties properties) {
        super(properties);
    }

    private static VoxelShape makeShape(Direction facing) {
        VoxelShape result = Shapes.empty();
        for (double[] p : PARTS) {
            double x0 = p[0], y0 = p[1], z0 = p[2];
            double x1 = p[3], y1 = p[4], z1 = p[5];
            VoxelShape part = switch (facing) {
                case NORTH -> Block.box(16 - x1, y0, 16 - z1, 16 - x0, y1, 16 - z0);
                case WEST -> Block.box(16 - z1, y0, x0, 16 - z0, y1, x1);
                case EAST -> Block.box(z0, y0, 16 - x1, z1, y1, 16 - x0);
                default -> Block.box(x0, y0, z0, x1, y1, z1);
            };
            result = Shapes.or(result, part);
        }
        return result.optimize();
    }

    private static VoxelShape shape(Direction facing) {
        return switch (facing) {
            case NORTH -> NORTH;
            case WEST -> WEST;
            case EAST -> EAST;
            default -> SOUTH;
        };
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shape(state.getValue(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shape(state.getValue(FACING));
    }
}
