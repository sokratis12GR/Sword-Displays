package com.sofodev.sworddisplay.blocks;

import com.sofodev.sworddisplay.SwordDisplay;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static net.minecraft.util.math.shapes.IBooleanFunction.OR;
import static net.minecraftforge.common.ToolType.PICKAXE;

public class SwordDisplayBlock extends Block {

    private static final List<Item> SPECIAL_ITEMS = Stream.of("tconstruct:broadsword",
            "tconstruct:cleaver", "tconstruct:rapier", "draconicevolution:wyvern_sword", "draconicevolution:draconic_sword",
            "tconstruct:longsword", "projecte:item.pe_dm_sword", "projecte:item.pe_rm_sword").map(SwordDisplayBlock::getItem).collect(Collectors.toList());

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    protected static final VoxelShape VOXEL = VoxelShapes.combineAndSimplify(Block.makeCuboidShape(0, 0, 0, 16, 2, 16), Block.makeCuboidShape(2, 2, 2, 14, 5, 14), OR);

    public SwordDisplayBlock() {
        super(Properties.create(Material.IRON).hardnessAndResistance(10.0f, 1000.0f).harvestLevel(1).harvestTool(PICKAXE));
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VOXEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VOXEL;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof SwordDisplayTile) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        TileEntity te = world.getTileEntity(pos);
        if (!world.isRemote && te instanceof SwordDisplayTile) {
            SwordDisplayTile displayTile = (SwordDisplayTile) te;
            if (player.isSneaking()) {
                if (player.getActiveHand() == Hand.MAIN_HAND && player.getHeldItemMainhand().isEmpty()) {
                    final ItemStack toDrop = displayTile.getSword().copy();
                    displayTile.setSword(ItemStack.EMPTY);
                    player.dropItem(toDrop, false);
                }
            } else {
                ItemStack stack = player.getHeldItem(hand);
                boolean isSword = stack.getItem() instanceof SwordItem || anyMatch(stack, SPECIAL_ITEMS);
                if (hand == Hand.MAIN_HAND && displayTile.getSword().isEmpty() && isSword) {
                    ItemStack copy = stack.copy();
                    displayTile.setSword(copy);
                    stack.shrink(1);
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    public boolean anyMatch(ItemStack stack, List<Item> items) {
        return items.stream().anyMatch(item -> stack.getItem() == item);
    }

    public static Item getItem(String name) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(name));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        ItemStack itemstack = super.getPickBlock(state, target, world, pos, player);
        SwordDisplayTile tileTrophy = (SwordDisplayTile) world.getTileEntity(pos);
        CompoundNBT nbttagcompound = tileTrophy.saveToNbt(new CompoundNBT());
        tileTrophy.markDirty();
        if (!nbttagcompound.isEmpty()) {
            itemstack.setTagInfo("BlockEntityTag", nbttagcompound);
        }
        return itemstack;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof SwordDisplayTile) {
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(),
                        ((SwordDisplayTile) tileentity).getSword().copy()
                );
            }
            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        if (!world.isRemote && te instanceof SwordDisplayTile) {
            SwordDisplayTile displayTile = (SwordDisplayTile) te;
            ItemStack stack = displayTile.getSword();
            if (!stack.isEmpty()) return calculateOutput(stack);
        }
        return 0;
    }

    private int calculateOutput(ItemStack stack){
        if (stack.isDamaged()) {
            int x = stack.getMaxDamage() / (stack.getMaxDamage() - stack.getDamage());
            x = x > 15 ? 14 : x == 15 ? 13 : x;
            return 15 / x;
        }
        return 15;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SwordDisplayTile();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return facing.getAxis().isHorizontal() ? stateIn : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return super.mirror(state, mirrorIn);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}