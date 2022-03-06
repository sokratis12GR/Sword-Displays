package com.sofodev.sworddisplay.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

import static com.sofodev.sworddisplay.SwordDisplay.RegistryEvents.*;
import static net.minecraft.world.item.Items.*;

public class Recipes extends RecipeProvider implements DataProvider, IConditionBuilder {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        this.registerMaterialRecipes(consumer, SWORD_DISPLAY, SWORD_CASE, STONE_SLAB, STONE_STAIRS, IRON_INGOT);
        this.registerMaterialRecipes(consumer, WOODEN_SWORD_DISPLAY, WOODEN_SWORD_CASE, OAK_SLAB, OAK_STAIRS, OAK_PLANKS);
        this.registerMaterialRecipes(consumer, ACACIA_SWORD_DISPLAY, ACACIA_SWORD_CASE, ACACIA_SLAB, ACACIA_STAIRS, ACACIA_PLANKS);
        this.registerMaterialRecipes(consumer, BIRCH_SWORD_DISPLAY, BIRCH_SWORD_CASE, BIRCH_SLAB, BIRCH_STAIRS, BIRCH_PLANKS);
        this.registerMaterialRecipes(consumer, DARK_OAK_SWORD_DISPLAY, DARK_OAK_SWORD_CASE, DARK_OAK_SLAB, DARK_OAK_STAIRS, DARK_OAK_PLANKS);
        this.registerMaterialRecipes(consumer, JUNGLE_SWORD_DISPLAY, JUNGLE_SWORD_CASE, JUNGLE_SLAB, JUNGLE_STAIRS, JUNGLE_PLANKS);
        this.registerMaterialRecipes(consumer, SPRUCE_SWORD_DISPLAY, SPRUCE_SWORD_CASE, SPRUCE_SLAB, SPRUCE_STAIRS, SPRUCE_PLANKS);
        this.registerMaterialRecipes(consumer, PRISMARINE_SWORD_DISPLAY, PRISMARINE_SWORD_CASE, PRISMARINE_SLAB, PRISMARINE_STAIRS, SEA_LANTERN);
        this.registerMaterialRecipes(consumer, IRON_SWORD_DISPLAY, IRON_SWORD_CASE, IRON_INGOT, STONE_BRICK_STAIRS, IRON_BLOCK);
        this.registerMaterialRecipes(consumer, GOLDEN_SWORD_DISPLAY, GOLDEN_SWORD_CASE, GOLD_INGOT, GOLD_INGOT, GOLD_BLOCK);
        this.registerMaterialRecipes(consumer, DIAMOND_SWORD_DISPLAY, DIAMOND_SWORD_CASE, Items.DIAMOND, Items.DIAMOND, DIAMOND_BLOCK);
        this.registerMaterialRecipes(consumer, EMERALD_SWORD_DISPLAY, EMERALD_SWORD_CASE, Items.EMERALD, Items.EMERALD, EMERALD_BLOCK);
    }

    private void registerMaterialRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Block> sdDisplay, RegistryObject<Block> sdCase, ItemLike top, ItemLike side, ItemLike core) {
        this.registerDisplayRecipes(consumer, sdDisplay, top, side, core);
        this.registerCaseRecipes(consumer, sdCase, top, side, core);
    }

    private void registerCaseRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Block> block, ItemLike top, ItemLike side, ItemLike core) {
        ShapedRecipeBuilder.shaped(block.get())
                .pattern("GGG")
                .pattern("GLG")
                .pattern("SCS")
                .define('L', top)
                .define('S', side)
                .define('C', core)
                .define('G', GLASS_PANE)
                .group("sworddisplay:case")
                .unlockedBy("has_core", has(core))
                .save(consumer);
    }

    private void registerDisplayRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Block> block, ItemLike top, ItemLike side, ItemLike core) {
        ShapedRecipeBuilder.shaped(block.get())
                .pattern(" L ")
                .pattern("SCS")
                .define('L', top)
                .define('S', side)
                .define('C', core)
                .group("sworddisplay:display")
                .unlockedBy("has_core", has(core))
                .save(consumer);
    }

    @Override
    public String getName() {
        return "Recipes";
    }
}
