package com.sofodev.sworddisplay.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.concurrent.CompletableFuture;

import static com.sofodev.sworddisplay.registry.ModBlocks.registryHelper;
import static net.minecraft.world.item.Items.GLASS_PANE;

public class Recipes extends RecipeProvider implements DataProvider, IConditionBuilder {

    public Recipes(DataGenerator generatorIn, CompletableFuture<HolderLookup.Provider> provider) {
        super(generatorIn.getPackOutput(), provider);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        registryHelper.forEach(entry -> {
            // Get the display/case blocks
            DeferredHolder<Block, Block> displayRO = entry.blocks().displayBlock();
            DeferredHolder<Block, Block> caseRO = entry.blocks().caseBlock();
            DeferredHolder<Block, Block> wallRO = entry.blocks().wallDisplay();

            // Get items for recipe ingredients
            ItemLike material = entry.coreMaterial();

            // Register recipes
            registerMaterialRecipes(consumer, displayRO, caseRO, wallRO, material, (ItemTags.SLABS));
        });
    }

    private void registerMaterialRecipes(RecipeOutput consumer,
                                         DeferredHolder<Block, Block> sdDisplay, DeferredHolder<Block, Block> sdCase, DeferredHolder<Block, Block> wallDisplay,
                                         ItemLike material, TagKey<Item> slabs) {

        this.registerDisplayRecipes(consumer, sdDisplay, material, slabs);
        this.registerCaseRecipes(consumer, sdCase, material, slabs);
        this.registerWallDisplayRecipes(consumer, wallDisplay, material, slabs);
    }

    private void registerCaseRecipes(RecipeOutput consumer, DeferredHolder<Block, Block> block, ItemLike material, TagKey<Item> core) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block.get())
                .pattern("GGG")
                .pattern("GMG")
                .pattern("MCM")
                .define('M', material)
                .define('C', Ingredient.of(core))
                .define('G', GLASS_PANE)
                .group("sworddisplay:case")
                .unlockedBy("has_core", has(core))
                .save(consumer);
    }

    private void registerWallDisplayRecipes(RecipeOutput consumer, DeferredHolder<Block, Block> block, ItemLike material, TagKey<Item> core) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block.get())
                .pattern(" G ")
                .pattern("GMG")
                .pattern(" G ")
                .define('M', material)
                .define('G', GLASS_PANE)
                .group("sworddisplay:wall_display")
                .unlockedBy("has_core", has(core))
                .save(consumer);
    }

    private void registerDisplayRecipes(RecipeOutput consumer, DeferredHolder<Block, Block> block, ItemLike material, TagKey<Item> core) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block.get())
                .pattern(" M ")
                .pattern("MCM")
                .define('M', material)
                .define('C', Ingredient.of(core))
                .group("sworddisplay:display")
                .unlockedBy("has_core", has(core))
                .save(consumer);
    }
}
