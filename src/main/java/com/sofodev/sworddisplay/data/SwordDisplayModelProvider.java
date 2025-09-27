package com.sofodev.sworddisplay.data;

import com.sofodev.sworddisplay.registry.ModBlocks;
import com.sofodev.sworddisplay.registry.RegistryHelper;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

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
            Block displayBlock = entry.getDisplayBlock().get();
            Block caseBlock = entry.getCaseBlock().get();
            String textureBase = getBaseNameWithoutDisplay(displayBlock);

            String displayModel = textureBase + "_sword_display";
            String caseModel = textureBase + "_sword_case";
            String key = entry.getKey();
            String mc_texture = key + "_block";
            if (Objects.equals(key, "stone") ||
                    Objects.equals(key, "obsidian") ||
                    Objects.equals(key, "stone_bricks") ||
                    Objects.equals(key, "end_stone_bricks") ||
                    Objects.equals(key, "netherrack") ||
                    Objects.equals(key, "nether_bricks"))
                mc_texture = key;
            if (Objects.equals(key, "quartz")) {
                mc_texture = key + "_block_side";
            }

            if (Objects.equals(key, "mangrove") || Objects.equals(key, "cherry") || Objects.equals(key, "crimson")) {
                mc_texture = key + "_planks";
            }
            ResourceLocation zeroTex = ResourceLocation.withDefaultNamespace("block/" + mc_texture);

            if (entry.isSpecial()) {
//                ResourceLocation modTex = ResourceLocation.fromNamespaceAndPath(MODID, "block/" + entry.getKey() + "_middle");

                // Display
                models().withExistingParent("block/" + displayModel, modLoc("block/base/sword_display_base"))
                        .texture("0", zeroTex)
                        .texture("1", zeroTex)
                        .texture("particle", zeroTex);

                // Case
                models().withExistingParent("block/" + caseModel, modLoc("block/base/sword_case_base"))
                        .texture("0", zeroTex)
                        .texture("1", zeroTex)
                        .texture("2", ResourceLocation.fromNamespaceAndPath(MODID, "block/glass"))
                        .texture("particle", zeroTex);

            } else {
                ResourceLocation topTex = ResourceLocation.fromNamespaceAndPath(MODID, "block/" + entry.getKey() + "_top");
                ResourceLocation botTex = ResourceLocation.fromNamespaceAndPath(MODID, "block/" + entry.getKey() + "_bot");

                // Display
                models().withExistingParent("block/" + displayModel, modLoc("block/base/sword_display_base"))
                        .texture("0", botTex)
                        .texture("1", topTex)
                        .texture("particle", topTex);

                // Case
                models().withExistingParent("block/" + caseModel, modLoc("block/base/sword_case_base"))
                        .texture("0", botTex)
                        .texture("1", topTex)
                        .texture("2", ResourceLocation.fromNamespaceAndPath(MODID, "block/glass"))
                        .texture("particle", topTex);
            }

            itemModels().getBuilder(displayModel).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + displayModel)));
            itemModels().getBuilder(caseModel).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + caseModel)));

            makeBlockState(displayBlock, displayModel);
            makeBlockState(caseBlock, caseModel);
        }

        System.out.println("Checking parent: " + modLoc("block/base/sword_display_base") +
                " exists=" + existingFileHelper.exists(modLoc("block/base/sword_display_base"),
                PackType.CLIENT_RESOURCES));
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
