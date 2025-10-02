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
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

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
            RegistryObject<Block> displayRO = entry.getDisplayBlock();
            RegistryObject<Block> caseRO = entry.getCaseBlock();

            // Get items for recipe ingredients
            ItemLike top = entry.getTopMaterial();
            ItemLike side = entry.getSideMaterial();
            ItemLike core = entry.getCoreMaterial();

            // Register recipes
            registerMaterialRecipes(consumer, displayRO, caseRO, top, side, (ItemTags.SLABS));
        });
    }

    private void registerMaterialRecipes(RecipeOutput consumer, RegistryObject<Block> sdDisplay, RegistryObject<Block> sdCase, ItemLike top, ItemLike side, TagKey<Item> core) {

        this.registerDisplayRecipes(consumer, sdDisplay, top, side, core);
        this.registerCaseRecipes(consumer, sdCase, top, side, core);
    }

    private void registerCaseRecipes(RecipeOutput consumer, RegistryObject<Block> block, ItemLike top, ItemLike side, TagKey<Item> core) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block.get())
                .pattern("GGG")
                .pattern("GLG")
                .pattern("SCS")
                .define('L', top)
                .define('S', side)
                .define('C', Ingredient.of(core))
                .define('G', GLASS_PANE)
                .group("sworddisplay:case")
                .unlockedBy("has_core", has(core))
                .save(consumer);
    }

    private void registerDisplayRecipes(RecipeOutput consumer, RegistryObject<Block> block, ItemLike top, ItemLike side, TagKey<Item> core) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block.get())
                .pattern(" L ")
                .pattern("SCS")
                .define('L', top)
                .define('S', side)
                .define('C', Ingredient.of(core))
                .group("sworddisplay:display")
                .unlockedBy("has_core", has(core))
                .save(consumer);
    }
}
