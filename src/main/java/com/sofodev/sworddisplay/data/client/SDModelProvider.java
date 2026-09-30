package com.sofodev.sworddisplay.data.client;

import com.sofodev.sworddisplay.SwordDisplay;
import com.sofodev.sworddisplay.blocks.SwordDisplayBlock;
import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.Optional;


public final class SDModelProvider extends ModelProvider {
    private static final TextureSlot TEXTURE_0 = TextureSlot.create("0");
    private static final TextureSlot TEXTURE_1 = TextureSlot.create("1");

    private static final ModelTemplate SWORD_DISPLAY = blockTemplate("sword_display_base");
    private static final ModelTemplate SWORD_CASE = blockTemplate("sword_case_base");
    private static final ModelTemplate WALL_DISPLAY = wallTemplate();

    public SDModelProvider(PackOutput output) {
        super(output, SwordDisplay.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        for (ModBlocks.BlockRegistryEntry entry : ModBlocks.REGISTRY_LIST) {
            Material material = materialTexture(entry.key());

            generate(blockModels, entry.blocks().displayBlock().get(), SWORD_DISPLAY, material);
            generate(blockModels, entry.blocks().caseBlock().get(), SWORD_CASE, material);
            generate(blockModels, entry.blocks().wallDisplay().get(), WALL_DISPLAY, material);
        }
    }

    private static void generate(BlockModelGenerators generator, Block block, ModelTemplate template, Material texture) {
        Identifier model = template.create(
                block,
                new TextureMapping()
                        .put(TEXTURE_0, texture)
                        .put(TEXTURE_1, texture)
                        .put(TextureSlot.PARTICLE, texture),
                generator.modelOutput
        );

        var variant = BlockModelGenerators.plainVariant(model);

        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block, variant)
                        .with(PropertyDispatch.modify(SwordDisplayBlock.FACING)
                                .select(Direction.SOUTH, BlockModelGenerators.NOP)
                                .select(Direction.WEST, BlockModelGenerators.Y_ROT_90)
                                .select(Direction.NORTH, BlockModelGenerators.Y_ROT_180)
                                .select(Direction.EAST, BlockModelGenerators.Y_ROT_270))
        );

    }

    private static ModelTemplate blockTemplate(String parent) {
        return new ModelTemplate(
                Optional.of(Identifier.fromNamespaceAndPath(SwordDisplay.MODID, "block/base/" + parent)),
                Optional.empty(),
                TEXTURE_0,
                TEXTURE_1,
                TextureSlot.PARTICLE
        );
    }

    private static ModelTemplate wallTemplate() {
        return new ModelTemplate(
                Optional.of(Identifier.fromNamespaceAndPath(SwordDisplay.MODID, "block/base/wall_display_base")),
                Optional.empty(),
                TEXTURE_0,
                TextureSlot.PARTICLE
        );
    }

    private static Material materialTexture(String key) {
        if (key.equals("quartz")) {
            return new Material(Identifier.withDefaultNamespace("block/quartz_block_side"));
        }

        Block materialBlock = ModBlocks.BLOCK_AND_ITEM_MAP.get(key).block();
        Identifier blockId = BuiltInRegistries.BLOCK.getKey(materialBlock);
        return new Material(Identifier.fromNamespaceAndPath(blockId.getNamespace(), "block/" + blockId.getPath()));
    }
}
