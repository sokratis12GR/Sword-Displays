package com.sofodev.sworddisplay.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
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
import java.util.function.Consumer;

import static com.sofodev.sworddisplay.registry.ModBlocks.registryHelper;
import static net.minecraft.world.item.Items.GLASS_PANE;

public class Recipes extends RecipeProvider implements DataProvider, IConditionBuilder {

    public Recipes(DataGenerator generatorIn, CompletableFuture<HolderLookup.Provider> provider) {
        super(generatorIn.getPackOutput());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        registryHelper.forEach(entry -> {
            // Get the display/case blocks
            RegistryObject<Block> displayRO = entry.blocks().displayBlock();
            RegistryObject<Block> caseRO = entry.blocks().caseBlock();
            RegistryObject<Block> wallRO = entry.blocks().wallDisplay();

            // Get items for recipe ingredients
            ItemLike material = entry.coreMaterial();

            // Register recipes
            registerMaterialRecipes(consumer, displayRO, caseRO, wallRO, material, (ItemTags.SLABS));
        });
    }

    private void registerMaterialRecipes(Consumer<FinishedRecipe> consumer,
                                         RegistryObject<Block> sdDisplay, RegistryObject<Block> sdCase, RegistryObject<Block> wallDisplay,
                                         ItemLike material, TagKey<Item> slabs) {

        this.registerDisplayRecipes(consumer, sdDisplay, material, slabs);
        this.registerCaseRecipes(consumer, sdCase, material, slabs);
        this.registerWallDisplayRecipes(consumer, wallDisplay, material, slabs);
    }

    private void registerCaseRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Block> block, ItemLike material, TagKey<Item> core) {
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

    private void registerWallDisplayRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Block> block, ItemLike material, TagKey<Item> core) {
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

    private void registerDisplayRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Block> block, ItemLike material, TagKey<Item> core) {
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