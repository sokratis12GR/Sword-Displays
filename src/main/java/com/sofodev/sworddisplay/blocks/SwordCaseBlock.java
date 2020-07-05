package com.sofodev.sworddisplay.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.lwjgl.opengl.GL;

import static net.minecraft.util.math.shapes.IBooleanFunction.*;
import static net.minecraftforge.common.ToolType.PICKAXE;

public class SwordCaseBlock extends SwordDisplayBlock {

    protected static final VoxelShape FULL_MODEL = VoxelShapes.or(Block.makeCuboidShape(0, 2, 0, 16, 21, 16), VOXEL);

    public SwordCaseBlock(ToolType type) {
        super(Properties.create(Material.GLASS).notSolid(), type);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return FULL_MODEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return FULL_MODEL;
    }
}