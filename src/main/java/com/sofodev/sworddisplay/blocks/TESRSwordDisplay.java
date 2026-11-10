package com.sofodev.sworddisplay.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import static com.sofodev.sworddisplay.blocks.SwordDisplayBlock.IS_REVERSE;
import static com.sofodev.sworddisplay.blocks.SwordWallDisplayBlock.IS_WALL_DISPLAY;

@OnlyIn(Dist.CLIENT)
public class TESRSwordDisplay implements BlockEntityRenderer<SwordDisplayTile> {

    private static final double[] NORTH_SOUTH_SLOT_X_PX = {3.2D, 8.0D, 12.8D};
    private static final double[] EAST_WEST_SLOT_X_PX = {5.0D, 8.0D, 11.0D};

    private final ItemRenderer renderer;

    public TESRSwordDisplay(BlockEntityRendererProvider.Context dispatcher) {
        this.renderer = dispatcher.getItemRenderer();
    }

    public void renderItem(SwordDisplayTile tile, ItemStack stack, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        matrix.pushPose();

        boolean isReverse = tile.getBlockState().getValue(IS_REVERSE);
        boolean isWallDisplay = tile.getBlockState().getOptionalValue(IS_WALL_DISPLAY).orElse(false);
        boolean isPlaqueWall = tile.getBlockState().getBlock() instanceof PlaqueWallBlock;
        Direction facing = tile.getBlockState().getValue(SwordDisplayBlock.FACING);

        var fullSize = new Vec3(1F, 1F, 1F);
        float wallScale = isPlaqueWall ? 0.66F : 0.33F;
        var wallSize = new Vec3(wallScale, wallScale, wallScale);
        var middleLocation = new Vec3(0.5F, 0.6F, 0.5F);

        if (!isWallDisplay) translateAndScale(matrix, middleLocation, fullSize);
        else {
            switch (facing) {
                case EAST -> translateAndScale(matrix, new Vec3(0.825F, 0.5F, 0.5F), wallSize);
                case WEST -> translateAndScale(matrix, new Vec3(0.175F, 0.5F, 0.5F), wallSize);
                case SOUTH -> translateAndScale(matrix, new Vec3(0.5F, 0.5F, 0.825F), wallSize);
                case NORTH -> translateAndScale(matrix, new Vec3(0.5F, 0.5F, 0.175F), wallSize);
            }
        }

        if (isReverse) {
            applyReverseTransform(matrix);
        }

        matrix.mulPose(Axis.YP.rotationDegrees(tile.getHorizontalRotation() * 45.0F));
        applyFacingRotation(matrix, facing, isReverse);
        matrix.mulPose(Axis.ZP.rotationDegrees(-tile.getTiltRotation() * 45.0F));

        renderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, matrix, buffer, tile.getLevel(), 1);

        matrix.popPose();
    }

    private void translateAndScale(PoseStack matrix, Vec3 location, Vec3 scale) {
        matrix.translate(location.x, location.y, location.z);
        matrix.scale((float) scale.x, (float) scale.y, (float) scale.z);
    }

    private void applyReverseTransform(PoseStack matrix) {
        matrix.translate(0, 0.03D, 0);
        matrix.scale(0.94F, 0.94F, 0.94F);
    }

    private void applyFacingRotation(PoseStack matrix, Direction facing, boolean isReverse) {
        if (isReverse) {
            switch (facing) {
                case WEST, EAST -> rotateItem(matrix, 0, 90f, -45f);
                case NORTH, SOUTH -> rotateItem(matrix, 0, 180f, -45f);
            }
        } else {
            switch (facing) {
                case WEST, EAST -> rotateItem(matrix, 180f, 90f, -45f);
                case NORTH, SOUTH -> rotateItem(matrix, 180f, 180f, -45f);
            }
        }
    }

    private void rotateItem(PoseStack matrix, float a, float b, float c) {
        matrix.mulPose(Axis.XP.rotationDegrees(a));
        matrix.mulPose(Axis.YP.rotationDegrees(b));
        matrix.mulPose(Axis.ZP.rotationDegrees(c));
    }

    @Override
    public void render(SwordDisplayTile tile, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (tile.isRack()) {
            renderRack(tile, matrix, buffer, combinedLight, combinedOverlay);
            return;
        }
        ItemStack sword = tile.getSword();
        if (!sword.isEmpty()) {
            this.renderItem(tile, sword, partialTicks, matrix, buffer, combinedLight, combinedOverlay);
        }
    }

    private void renderRack(SwordDisplayTile tile, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Direction facing = tile.getBlockState().getValue(SwordDisplayBlock.FACING);
        boolean isWallDisplay = tile.getBlockState().getOptionalValue(IS_WALL_DISPLAY).orElse(false);
        boolean isCase = tile.getBlockState().getBlock() instanceof RackCaseBlock;
        boolean eastWest = facing == Direction.EAST || facing == Direction.WEST;

        double mountZPixels = isWallDisplay ? (eastWest ? 3.25D : 13.75D)
                : isCase ? (eastWest ? 3.25D : 12.75D)
                : (eastWest ? 5.25D : 10.75D);
        double mountYPixels = isWallDisplay ? 8.0D : 10.0D;
        double[] slotXPixels = eastWest ? EAST_WEST_SLOT_X_PX : NORTH_SOUTH_SLOT_X_PX;

        matrix.pushPose();
        matrix.translate(0.5D, 0.0D, 0.5D);
        matrix.mulPose(Axis.YP.rotationDegrees(facing.toYRot()));

        for (int i = 0; i < 3; i++) {
            ItemStack weapon = tile.getWeapon(i);
            if (weapon.isEmpty()) continue;
            matrix.pushPose();
            matrix.translate((slotXPixels[i] - 8.0D) / 16.0D, mountYPixels / 16.0D, (mountZPixels - 8.0D) / 16.0D);
            matrix.scale(0.33F, 0.44F, 0.33F);
            matrix.mulPose(Axis.YP.rotationDegrees(tile.getRackHorizontalRotation(i) * 45.0F));
            matrix.mulPose(Axis.ZP.rotationDegrees(-tile.getRackTiltRotation(i) * 45.0F));
            rotateItem(matrix, 180.0F, 180.0F, -45.0F);
            renderer.renderStatic(weapon, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, matrix, buffer, tile.getLevel(), 1);
            matrix.popPose();
        }
        matrix.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(SwordDisplayTile p_112306_) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 256;
    }

}