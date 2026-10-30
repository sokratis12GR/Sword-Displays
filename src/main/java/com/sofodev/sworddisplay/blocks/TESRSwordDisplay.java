package com.sofodev.sworddisplay.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.sofodev.thedragonlib.client.util.PoseStackHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;

import static com.sofodev.sworddisplay.blocks.SwordDisplayBlock.IS_REVERSE;
import static com.sofodev.sworddisplay.blocks.SwordWallDisplayBlock.IS_WALL_DISPLAY;

public class TESRSwordDisplay implements BlockEntityRenderer<SwordDisplayTile, TESRSwordDisplay.State> {


    private static final double[] NORTH_SOUTH_SLOT_X_PX = {3.2D, 8.0D, 12.8D};
    private static final double[] EAST_WEST_SLOT_X_PX = {5.0D, 8.0D, 11.0D};

    public TESRSwordDisplay(BlockEntityRendererProvider.Context context) {
    }

    public static class State extends BlockEntityRenderState {
        final ItemStackRenderState sword = new ItemStackRenderState();
        final ItemStackRenderState[] rackSwords = {new ItemStackRenderState(), new ItemStackRenderState(), new ItemStackRenderState()};
        final int[] rackHorizontal = new int[3];
        final int[] rackTilt = new int[3];
        boolean rack;
        boolean reverse;
        boolean wallDisplay;
        boolean caseDisplay;
        boolean plaqueWall;
        int horizontalRotation;
        int tiltRotation;
        Direction facing = Direction.NORTH;
    }

    @Override
    public State createRenderState() {
        return new State();
    }

    @Override
    public void extractRenderState(SwordDisplayTile tile, State state, float partialTick, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(tile, state, partialTick, cameraPosition, breakProgress);
        var blockState = tile.getBlockState();
        String blockName = BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).getPath();
        state.plaqueWall = blockName.endsWith("_wall_display_plaque");
        state.reverse = blockState.getValue(IS_REVERSE);
        state.rack = blockName.endsWith("_rack");
        state.wallDisplay = blockState.getOptionalValue(IS_WALL_DISPLAY).orElse(false);
        state.caseDisplay = blockName.contains("_sword_case_");
        state.facing = blockState.getValue(SwordDisplayBlock.FACING);
        state.horizontalRotation = tile.getHorizontalRotation();
        state.tiltRotation = tile.getTiltRotation();
        Minecraft.getInstance().getItemModelResolver().updateForTopItem(
                state.sword, tile.getSword(), ItemDisplayContext.FIXED, tile.getLevel(), null, 1);
        if (state.rack) for (int i = 0; i < 3; i++) {
            state.rackHorizontal[i] = tile.getRackHorizontalRotation(i);
            state.rackTilt[i] = tile.getRackTiltRotation(i);
            Minecraft.getInstance().getItemModelResolver().updateForTopItem(
                    state.rackSwords[i], tile.getWeapon(i), ItemDisplayContext.FIXED, tile.getLevel(), null, 1);
        }
    }

    @Override
    public void submit(State state, PoseStack pose, SubmitNodeCollector collector, CameraRenderState camera) {
        if (state.rack) {
            // Rotate rack-local slot positions with the block facing.
            pose.pushPose();
            pose.translate(0.5D, 0.0D, 0.5D);
            pose.mulPose(Axis.YP.rotationDegrees(state.facing.toYRot()));

            final boolean eastWest = state.facing == Direction.EAST || state.facing == Direction.WEST;
            final double mountZPixels;
            if (state.wallDisplay) {
                mountZPixels = eastWest ? 3.25D : 13.75D;
            } else if (state.caseDisplay) {
                mountZPixels = eastWest ? 3.25D : 12.75D;
            } else {
                mountZPixels = eastWest ? 5.25D : 10.75D;
            }
            final double mountYPixels = state.wallDisplay ? 8.0D : 10.0D;
            final double[] slotXPixels = eastWest ? EAST_WEST_SLOT_X_PX : NORTH_SOUTH_SLOT_X_PX;

            for (int i = 0; i < 3; i++) {
                if (state.rackSwords[i].isEmpty()) continue;
                pose.pushPose();
                pose.translate((slotXPixels[i] - 8.0D) / 16.0D,
                        mountYPixels / 16.0D,
                        (mountZPixels - 8.0D) / 16.0D);

                pose.scale(0.33F, 0.44F, 0.33F);
                pose.mulPose(Axis.YP.rotationDegrees(state.rackHorizontal[i] * 45.0F));
                pose.mulPose(Axis.ZP.rotationDegrees(-state.rackTilt[i] * 45.0F));
                PoseStackHelper.rotateItem(pose, 180.0F, 180.0F, -45.0F);
                state.rackSwords[i].submit(pose, collector, state.lightCoords, 0, 0);
                pose.popPose();
            }
            pose.popPose();
            return;
        }
        if (state.sword.isEmpty()) return;
        pose.pushPose();
        if (!state.wallDisplay) PoseStackHelper.translateAndScale(pose, new Vec3(0.5F, 0.6F, 0.5F), new Vec3(1F, 1F, 1F));
        else {
            // Plaques display a larger weapon; original wall displays keep their scale.
            float size = state.plaqueWall ? 0.66F : 0.33F;
            Vec3 scale = new Vec3(size, size, size);
            switch (state.facing) {
                case EAST -> PoseStackHelper.translateAndScale(pose, new Vec3(0.825F, 0.5F, 0.5F), scale);
                case WEST -> PoseStackHelper.translateAndScale(pose, new Vec3(0.175F, 0.5F, 0.5F), scale);
                case SOUTH -> PoseStackHelper.translateAndScale(pose, new Vec3(0.5F, 0.5F, 0.825F), scale);
                case NORTH -> PoseStackHelper.translateAndScale(pose, new Vec3(0.5F, 0.5F, 0.175F), scale);
                default -> { }
            }
        }
        if (state.reverse) {
            pose.translate(0, 0.03D, 0);
            pose.scale(0.94F, 0.94F, 0.94F);
        }
        pose.mulPose(Axis.YP.rotationDegrees(state.horizontalRotation * 45.0F));
        applyFacingRotation(pose, state.facing, state.reverse);
        pose.mulPose(Axis.ZP.rotationDegrees(-state.tiltRotation * 45.0F));
        state.sword.submit(pose, collector, state.lightCoords, 0, 0);
        pose.popPose();
    }

    private static void applyFacingRotation(PoseStack pose, Direction facing, boolean reverse) {
        if (reverse) {
            switch (facing) {
                case WEST, EAST -> PoseStackHelper.rotateItem(pose, 0, 90f, -45f);
                case NORTH, SOUTH -> PoseStackHelper.rotateItem(pose, 0, 180f, -45f);
                default -> {
                }
            }
        } else {
            switch (facing) {
                case WEST, EAST -> PoseStackHelper.rotateItem(pose, 180f, 90f, -45f);
                case NORTH, SOUTH -> PoseStackHelper.rotateItem(pose, 180f, 180f, -45f);
                default -> {
                }
            }
        }
    }

    @Override
    public int getViewDistance() {
        return 256;
    }
}
