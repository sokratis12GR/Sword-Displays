package com.sofodev.sworddisplay.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.sofodev.sworddisplay.blocks.SwordDisplayBlock.IS_REVERSE;
import static net.minecraft.client.renderer.block.model.ItemTransforms.TransformType.FIXED;

@OnlyIn(Dist.CLIENT)
public class TESRSwordDisplay implements BlockEntityRenderer<SwordDisplayTile> {

    public TESRSwordDisplay(BlockEntityRendererProvider.Context dispatcher) {
//        super(dispatcher);
    }

    public void renderItem(SwordDisplayTile tile, ItemStack stack, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        matrix.pushPose();

        matrix.translate(0.5D, 0.6D, 0.5D);
        matrix.scale(1F, 1F, 1F);
        if (tile.getBlockState().getValue(IS_REVERSE)) {
            matrix.translate(0, 0.03D, 0);
            matrix.scale(0.94F, 0.94F, 0.94F);
            switch (tile.getBlockState().getValue(SwordDisplayBlock.FACING)) {
                case WEST:
                case EAST:
                    this.rotateItem(matrix, 0, 90f, -45f); // default values
                    break;
                case NORTH:
                case SOUTH:
                    this.rotateItem(matrix, 0, 180f, -45f);
                    break;
            }
        } else {
            switch (tile.getBlockState().getValue(SwordDisplayBlock.FACING)) {
                case WEST:
                case EAST:
                    this.rotateItem(matrix, 180f, 90f, -45f); // default values
                    break;
                case NORTH:
                case SOUTH:
                    this.rotateItem(matrix, 180f, 180f, -45f);
                    break;
            }
        }
        renderer.renderStatic(stack, FIXED, combinedLight, combinedOverlay, matrix, buffer, 1);

        matrix.popPose();
    }

    private void rotateItem(PoseStack matrix, float a, float b, float c) {
        matrix.mulPose(Vector3f.XP.rotationDegrees(a));
        matrix.mulPose(Vector3f.YP.rotationDegrees(b));
        matrix.mulPose(Vector3f.ZP.rotationDegrees(c));
    }

    @Override
    public void render(SwordDisplayTile tile, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        ItemStack sword = tile.getSword();
        if (!sword.isEmpty()) {
            this.renderItem(tile, sword, partialTicks, matrix, buffer, combinedLight, combinedOverlay);
        }
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