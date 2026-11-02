package com.sofodev.sworddisplay.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

final class RackModelShapes {
    private RackModelShapes() {}
    private static final VoxelShape[] DISPLAY = new VoxelShape[]{
        Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(2, 2, 2, 14, 5, 14),
            Block.box(2, 5, 11, 4, 13, 13),
            Block.box(12, 5, 11, 14, 13, 13),
            Block.box(4, 10, 11, 12, 12, 13),
            Block.box(5, 7, 11, 11, 8, 13)
        ), // SOUTH
        Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(2, 2, 2, 14, 5, 14),
            Block.box(3, 5, 2, 5, 13, 4),
            Block.box(3, 5, 12, 5, 13, 14),
            Block.box(3, 10, 4, 5, 12, 12),
            Block.box(3, 7, 5, 5, 8, 11)
        ), // WEST
        Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(2, 2, 2, 14, 5, 14),
            Block.box(12, 5, 3, 14, 13, 5),
            Block.box(2, 5, 3, 4, 13, 5),
            Block.box(4, 10, 3, 12, 12, 5),
            Block.box(5, 7, 3, 11, 8, 5)
        ), // NORTH
        Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(2, 2, 2, 14, 5, 14),
            Block.box(11, 5, 12, 13, 13, 14),
            Block.box(11, 5, 2, 13, 13, 4),
            Block.box(11, 10, 4, 13, 12, 12),
            Block.box(11, 7, 5, 13, 8, 11)
        ), // EAST
    };
    static VoxelShape display(Direction facing) {
        return DISPLAY[switch (facing) {
            case WEST -> 1;
            case NORTH -> 2;
            case EAST -> 3;
            default -> 0;
        }];
    }
    private static final VoxelShape[] CASESHAPE = new VoxelShape[]{
        Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(2, 2, 2, 14, 5, 14),
            Block.box(0, 2, 0, 16, 9, 16),
            Block.box(0, 9, 0, 16, 21, 16),
            Block.box(2, 5, 13, 4, 19, 14),
            Block.box(12, 5, 13, 14, 19, 14),
            Block.box(4, 15, 13, 12, 17, 14),
            Block.box(5, 10, 13, 11, 11, 14)
        ), // SOUTH
        Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(2, 2, 2, 14, 5, 14),
            Block.box(0, 2, 0, 16, 9, 16),
            Block.box(0, 9, 0, 16, 21, 16),
            Block.box(2, 5, 2, 3, 19, 4),
            Block.box(2, 5, 12, 3, 19, 14),
            Block.box(2, 15, 4, 3, 17, 12),
            Block.box(2, 10, 5, 3, 11, 11)
        ), // WEST
        Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(2, 2, 2, 14, 5, 14),
            Block.box(0, 2, 0, 16, 9, 16),
            Block.box(0, 9, 0, 16, 21, 16),
            Block.box(12, 5, 2, 14, 19, 3),
            Block.box(2, 5, 2, 4, 19, 3),
            Block.box(4, 15, 2, 12, 17, 3),
            Block.box(5, 10, 2, 11, 11, 3)
        ), // NORTH
        Shapes.or(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(2, 2, 2, 14, 5, 14),
            Block.box(0, 2, 0, 16, 9, 16),
            Block.box(0, 9, 0, 16, 21, 16),
            Block.box(13, 5, 12, 14, 19, 14),
            Block.box(13, 5, 2, 14, 19, 4),
            Block.box(13, 15, 4, 14, 17, 12),
            Block.box(13, 10, 5, 14, 11, 11)
        ), // EAST
    };
    static VoxelShape caseShape(Direction facing) {
        return CASESHAPE[switch (facing) {
            case WEST -> 1;
            case NORTH -> 2;
            case EAST -> 3;
            default -> 0;
        }];
    }
    private static final VoxelShape[] WALL = new VoxelShape[]{
        Shapes.or(
            Block.box(2, 3, 14, 4, 13, 16),
            Block.box(12, 3, 14, 14, 13, 16),
            Block.box(4, 10, 14, 12, 12, 16),
            Block.box(4, 5, 14, 12, 7, 16),
            Block.box(5, 7, 13, 6, 9, 16),
            Block.box(10, 8, 13, 11, 10, 16)
        ), // SOUTH
        Shapes.or(
            Block.box(0, 3, 2, 2, 13, 4),
            Block.box(0, 3, 12, 2, 13, 14),
            Block.box(0, 10, 4, 2, 12, 12),
            Block.box(0, 5, 4, 2, 7, 12),
            Block.box(0, 7, 5, 3, 9, 6),
            Block.box(0, 8, 10, 3, 10, 11)
        ), // WEST
        Shapes.or(
            Block.box(12, 3, 0, 14, 13, 2),
            Block.box(2, 3, 0, 4, 13, 2),
            Block.box(4, 10, 0, 12, 12, 2),
            Block.box(4, 5, 0, 12, 7, 2),
            Block.box(10, 7, 0, 11, 9, 3),
            Block.box(5, 8, 0, 6, 10, 3)
        ), // NORTH
        Shapes.or(
            Block.box(14, 3, 12, 16, 13, 14),
            Block.box(14, 3, 2, 16, 13, 4),
            Block.box(14, 10, 4, 16, 12, 12),
            Block.box(14, 5, 4, 16, 7, 12),
            Block.box(13, 7, 10, 16, 9, 11),
            Block.box(13, 8, 5, 16, 10, 6)
        ), // EAST
    };
    static VoxelShape wall(Direction facing) {
        return WALL[switch (facing) {
            case WEST -> 1;
            case NORTH -> 2;
            case EAST -> 3;
            default -> 0;
        }];
    }
}
