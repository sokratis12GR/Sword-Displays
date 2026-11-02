package com.sofodev.sworddisplay.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

final class PlaqueModelShapes {
    private PlaqueModelShapes() {}

    private static final double[][] DISPLAY_PARTS = {
        {0, 0, 0, 16, 2, 16},
        {2, 2, 2, 14, 5, 14},
        {3, 5, 12, 13, 13, 14},
        {5, 13, 12, 11, 15, 14},
        {2, 7, 12, 3, 11, 14},
        {13, 7, 12, 14, 11, 14},
    };
    private static final VoxelShape[] DISPLAY = create(DISPLAY_PARTS);
    static VoxelShape display(Direction facing) {
        return DISPLAY[switch (facing) {
            case WEST -> 1;
            case NORTH -> 2;
            case EAST -> 3;
            default -> 0;
        }];
    }

    private static final double[][] CASE_PARTS = {
        {0, 0, 0, 16, 2, 16},
        {2, 2, 2, 14, 5, 14},
        {0, 2, 0, 16, 9, 16},
        {0, 9, 0, 16, 21, 16},
        {3, 5, 13, 13, 17, 14},
        {5, 17, 13, 11, 19, 14},
        {2, 8, 13, 3, 14, 14},
        {13, 8, 13, 14, 14, 14},
    };
    private static final VoxelShape[] CASE = create(CASE_PARTS);
    static VoxelShape Vcase(Direction facing) {
        return CASE[switch (facing) {
            case WEST -> 1;
            case NORTH -> 2;
            case EAST -> 3;
            default -> 0;
        }];
    }

    private static VoxelShape[] create(double[][] parts) {
        return new VoxelShape[] {
            rotated(parts, Direction.SOUTH), rotated(parts, Direction.WEST),
            rotated(parts, Direction.NORTH), rotated(parts, Direction.EAST)
        };
    }

    private static VoxelShape rotated(double[][] parts, Direction facing) {
        VoxelShape shape = Shapes.empty();
        for (double[] p : parts) {
            double x0 = p[0], y0 = p[1], z0 = p[2];
            double x1 = p[3], y1 = p[4], z1 = p[5];
            VoxelShape part = switch (facing) {
                case NORTH -> Block.box(16 - x1, y0, 16 - z1, 16 - x0, y1, 16 - z0);
                case WEST -> Block.box(16 - z1, y0, x0, 16 - z0, y1, x1);
                case EAST -> Block.box(z0, y0, 16 - x1, z1, y1, 16 - x0);
                default -> Block.box(x0, y0, z0, x1, y1, z1);
            };
            shape = Shapes.or(shape, part);
        }
        return shape.optimize();
    }
}
