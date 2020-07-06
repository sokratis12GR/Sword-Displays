package com.sofodev.sworddisplay.blocks;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.sofodev.sworddisplay.blocks.SwordDisplayBlock.IS_REVERSE;
import static net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType.FIXED;

@OnlyIn(Dist.CLIENT)
public class TESRSwordDisplay extends TileEntityRenderer<SwordDisplayTile> {

    public TESRSwordDisplay(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    private void renderItem(TileEntity te, ItemStack stack, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        matrixStack.push();

        matrixStack.translate(0.5D, 0.6D, 0.5D);
        matrixStack.scale(1F, 1F, 1F);
        if (te.getBlockState().get(IS_REVERSE)) {
            matrixStack.translate(0, 0.03D, 0);
            matrixStack.scale(0.94F, 0.94F, 0.94F);
            switch (te.getBlockState().get(SwordDisplayBlock.FACING)) {
                case WEST:
                case EAST:
                    this.rotateItem(matrixStack, 0, 90f, -45f); // default values
                    break;
                case NORTH:
                case SOUTH:
                    this.rotateItem(matrixStack, 0, 180f, -45f);
                    break;
            }
        } else {
            switch (te.getBlockState().get(SwordDisplayBlock.FACING)) {
                case WEST:
                case EAST:
                    this.rotateItem(matrixStack, 180f, 90f, -45f); // default values
                    break;
                case NORTH:
                case SOUTH:
                    this.rotateItem(matrixStack, 180f, 180f, -45f);
                    break;
            }
        }
        renderer.renderItem(stack, FIXED, combinedLight, combinedOverlay, matrixStack, buffer);

        matrixStack.pop();
    }

    private void rotateItem(MatrixStack matrixStack, float a, float b, float c) {
        matrixStack.rotate(Vector3f.XP.rotationDegrees(a));
        matrixStack.rotate(Vector3f.YP.rotationDegrees(b));
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(c));
    }

    @Override
    public void render(SwordDisplayTile te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        ItemStack sword = te.getSword();
        if (!sword.isEmpty()) {
            this.renderItem(te, sword, matrixStack, buffer, combinedLight, combinedOverlay);
        }
    }

    @Override
    public boolean isGlobalRenderer(SwordDisplayTile te) {
        return true;
    }
}