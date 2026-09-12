package com.sofodev.sworddisplay.data;

import com.sofodev.sworddisplay.registry.ModBlocks;
import com.sofodev.sworddisplay.registry.RegistryHelper;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static com.sofodev.sworddisplay.registry.ModBlocks.getBaseNameWithoutDisplay;

public class SwordDisplayModelProvider extends BlockStateProvider {

    private final ExistingFileHelper existingFileHelper;
    private final RegistryHelper registryHelper;

    public SwordDisplayModelProvider(PackOutput output, ExistingFileHelper existingFileHelper, RegistryHelper registryHelper) {
        super(output, MODID, existingFileHelper);
        this.existingFileHelper = existingFileHelper;
        this.registryHelper = registryHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        List<ModBlocks.BlockRegistryEntry> entries = registryHelper.getRegistryList(); // assuming getter exists

        for (ModBlocks.BlockRegistryEntry entry : entries) {
            Block displayBlock = entry.blocks().displayBlock().get();
            Block caseBlock = entry.blocks().caseBlock().get();
            Block wallDisplayBlock = entry.blocks().wallDisplay().get();
            String textureBase = getBaseNameWithoutDisplay(displayBlock);

            String displayModel = textureBase + "_sword_display";
            String caseModel = textureBase + "_sword_case";
            String wallDisplayModel = textureBase + "_wall_display";
            String mc_texture = getMcTexture(entry);
            ResourceLocation zeroTex = ResourceLocation.withDefaultNamespace("block/" + mc_texture);

            models().withExistingParent("block/" + displayModel, modLoc("block/base/sword_display_base"))
                    .texture("0", zeroTex)
                    .texture("1", zeroTex)
                    .texture("particle", zeroTex);
            models().withExistingParent("block/" + wallDisplayModel, modLoc("block/base/wall_display_base"))
                    .texture("0", zeroTex)
                    .texture("particle", zeroTex);
            models().withExistingParent("block/" + caseModel, modLoc("block/base/sword_case_base"))
                    .texture("0", zeroTex)
                    .texture("1", zeroTex)
                    .texture("particle", zeroTex);

            itemModels().getBuilder(displayModel).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + displayModel)));
            itemModels().getBuilder(caseModel).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + caseModel)));
            itemModels().getBuilder(wallDisplayModel).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + wallDisplayModel)));

            makeBlockState(displayBlock, displayModel);
            makeBlockState(caseBlock, caseModel);
            makeBlockState(wallDisplayBlock, wallDisplayModel);
        }

        System.out.println("Checking parent: " + modLoc("block/base/sword_display_base") +
                " exists=" + existingFileHelper.exists(modLoc("block/base/sword_display_base"),
                PackType.CLIENT_RESOURCES));
    }

    private static @NotNull String getMcTexture(ModBlocks.BlockRegistryEntry entry) {
        String key = entry.key();
        String mc_texture = key + "_block";
        if (Objects.equals(key, "stone") ||
                Objects.equals(key, "obsidian") ||
                Objects.equals(key, "stone_bricks") ||
                Objects.equals(key, "end_stone_bricks") ||
                Objects.equals(key, "netherrack") ||
                Objects.equals(key, "nether_bricks") ||
                Objects.equals(key, "prismarine"))
            mc_texture = key;
        if (Objects.equals(key, "quartz")) {
            mc_texture = key + "_block_side";
        }

        if (entry.isWooden()) {
            mc_texture = key + "_planks";
        }
        return mc_texture;
    }


    private void makeBlockState(Block block, String modelName) {
        getVariantBuilder(block).forAllStates(state -> {
            int yRot = 0;
            if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                yRot = (int) facing.toYRot();
            }

            ModelFile modelFile = models().getExistingFile(modLoc("block/" + modelName));

            return ConfiguredModel.builder()
                    .modelFile(modelFile)
                    .rotationY(yRot % 360)
                    .build();
        });
    }

}
