package com.sofodev.sworddisplay.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;

import static com.sofodev.sworddisplay.blocks.SwordDisplayBlock.IS_REVERSE;
import static com.sofodev.sworddisplay.blocks.SwordWallDisplayBlock.IS_WALL_DISPLAY;

public class TESRSwordDisplay implements BlockEntityRenderer<SwordDisplayTile, TESRSwordDisplay.State> {
    public TESRSwordDisplay(BlockEntityRendererProvider.Context context) {
    }

    public static class State extends BlockEntityRenderState {
        final ItemStackRenderState sword = new ItemStackRenderState();
        boolean reverse;
        boolean wallDisplay;
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
        state.reverse = blockState.getValue(IS_REVERSE);
        state.wallDisplay = blockState.getOptionalValue(IS_WALL_DISPLAY).orElse(false);
        state.facing = blockState.getValue(SwordDisplayBlock.FACING);
        Minecraft.getInstance().getItemModelResolver().updateForTopItem(
                state.sword, tile.getSword(), ItemDisplayContext.FIXED, tile.getLevel(), null, 1);
    }

    @Override
    public void submit(State state, PoseStack pose, SubmitNodeCollector collector, CameraRenderState camera) {
        if (state.sword.isEmpty()) return;
        pose.pushPose();
        if (!state.wallDisplay) translateAndScale(pose, new Vec3(0.5F, 0.6F, 0.5F), new Vec3(1F, 1F, 1F));
        else switch (state.facing) {
            case EAST -> translateAndScale(pose, new Vec3(0.825F, 0.5F, 0.5F), new Vec3(0.33F, 0.33F, 0.33F));
            case WEST -> translateAndScale(pose, new Vec3(0.175F, 0.5F, 0.5F), new Vec3(0.33F, 0.33F, 0.33F));
            case SOUTH -> translateAndScale(pose, new Vec3(0.5F, 0.5F, 0.825F), new Vec3(0.33F, 0.33F, 0.33F));
            case NORTH -> translateAndScale(pose, new Vec3(0.5F, 0.5F, 0.175F), new Vec3(0.33F, 0.33F, 0.33F));
            default -> {
            }
        }
        if (state.reverse) {
            pose.translate(0, 0.03D, 0);
            pose.scale(0.94F, 0.94F, 0.94F);
        }
        applyFacingRotation(pose, state.facing, state.reverse);
        state.sword.submit(pose, collector, state.lightCoords, 0, 0);
        pose.popPose();
    }

    private static void translateAndScale(PoseStack pose, Vec3 location, Vec3 scale) {
        pose.translate(location.x, location.y, location.z);
        pose.scale((float) scale.x, (float) scale.y, (float) scale.z);
    }

    private static void applyFacingRotation(PoseStack pose, Direction facing, boolean reverse) {
        if (reverse) {
            switch (facing) {
                case WEST, EAST -> rotateItem(pose, 0, 90f, -45f);
                case NORTH, SOUTH -> rotateItem(pose, 0, 180f, -45f);
                default -> {
                }
            }
        } else {
            switch (facing) {
                case WEST, EAST -> rotateItem(pose, 180f, 90f, -45f);
                case NORTH, SOUTH -> rotateItem(pose, 180f, 180f, -45f);
                default -> {
                }
            }
        }
    }

    private static void rotateItem(PoseStack pose, float a, float b, float c) {
        pose.mulPose(Axis.XP.rotationDegrees(a));
        pose.mulPose(Axis.YP.rotationDegrees(b));
        pose.mulPose(Axis.ZP.rotationDegrees(c));
    }

    @Override
    public int getViewDistance() {
        return 256;
    }
}
