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
            RegistryObject<Block> displayRO = entry.blocks().displayBlock();
            RegistryObject<Block> caseRO = entry.blocks().caseBlock();
            RegistryObject<Block> wallRO = entry.blocks().wallDisplay();

            // Get items for recipe ingredients
            ItemLike material = entry.coreMaterial();

            // Register recipes
            registerMaterialRecipes(consumer, displayRO, caseRO, wallRO, material, (ItemTags.SLABS));
        });
    }

    private void registerMaterialRecipes(RecipeOutput consumer,
                                         RegistryObject<Block> sdDisplay, RegistryObject<Block> sdCase, RegistryObject<Block> wallDisplay,
                                         ItemLike material, TagKey<Item> slabs) {

        this.registerDisplayRecipes(consumer, sdDisplay, material, slabs);
        this.registerCaseRecipes(consumer, sdCase, material, slabs);
        this.registerWallDisplayRecipes(consumer, wallDisplay, material, slabs);
    }

    private void registerCaseRecipes(RecipeOutput consumer, RegistryObject<Block> block, ItemLike material, TagKey<Item> core) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block.get())
                .pattern("GGG")
                .pattern("GMG")
                .pattern("MCM")
                .define('M', material)
                .define('C', Ingredient.of(core))
                .define('G', GLASS_PANE)
                .group("sworddisplay:case_" + getMaterialFamily(block.getId().getPath().replace("_sword_case", "")))
                .unlockedBy("has_material", has(material))
                .save(consumer);
    }

    private void registerWallDisplayRecipes(RecipeOutput consumer, RegistryObject<Block> block, ItemLike material, TagKey<Item> core) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block.get())
                .pattern(" G ")
                .pattern("GMG")
                .pattern(" G ")
                .define('M', material)
                .define('G', GLASS_PANE)
                .group("sworddisplay:wall_display_" + getMaterialFamily(block.getId().getPath().replace("_wall_display", "")))
                .unlockedBy("has_material", has(material))
                .save(consumer);
    }

    private void registerDisplayRecipes(RecipeOutput consumer, RegistryObject<Block> block, ItemLike material, TagKey<Item> core) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block.get())
                .pattern(" M ")
                .pattern("MCM")
                .define('M', material)
                .define('C', Ingredient.of(core))
                .group("sworddisplay:display_" + getMaterialFamily(block.getId().getPath().replace("_sword_display", "")))
                .unlockedBy("has_material", has(material))
                .save(consumer);
    }
    private static String getMaterialFamily(String material) {

        if (containsAny(material, "oak", "spruce", "birch", "jungle", "acacia", "mangrove", "cherry", "bamboo", "crimson", "warped", "planks")) {
            return "wood";
        }

        if (material.contains("wool")) {
            return "wool";
        }

        if (material.contains("glazed_terracotta")) {
            return "glazed_terracotta";
        }

        if (material.contains("terracotta")) {
            return "terracotta";
        }

        if (material.contains("concrete_powder")) {
            return "concrete_powder";
        }

        if (material.contains("concrete")) {
            return "concrete";
        }

        if (material.contains("stained_glass")) {
            return "stained_glass";
        }

        if (containsAny(material, "copper", "iron", "gold", "netherite", "diamond", "emerald", "lapis", "redstone", "coal", "ore", "raw_")) {
            return "metals_and_ores";
        }

        if (containsAny(material, "sandstone", "quartz")) {
            return "sandstone_and_quartz";
        }

        if (containsAny(material, "deepslate", "cobblestone", "stone", "granite", "diorite", "andesite", "tuff", "calcite", "dripstone")) {
            return "stone";
        }

        if (containsAny(material, "bricks", "brick", "mud")) {
            return "bricks";
        }

        if (containsAny(material, "nether", "netherrack", "blackstone", "soul_", "nylium", "shroomlight", "basalt", "end_", "purpur")) {
            return "nether_and_end";
        }

        if (containsAny(material, "prismarine", "sponge", "sculk", "moss", "sea_lantern")) {
            return "ocean_and_underground";
        }

        if (containsAny(material, "glass", "glowstone", "lamp", "froglight")) {
            return "glass_and_lighting";
        }

        if (containsAny(material, "dirt", "sand", "gravel", "clay", "grass", "podzol", "mycelium")) {
            return "earth";
        }

        return "decorative";
    }

    private static boolean containsAny(String value, String... keywords) {
        for (String keyword : keywords) {
            if (value.contains(keyword)) {
                return true;
            }
        }
        return false;
    }


}