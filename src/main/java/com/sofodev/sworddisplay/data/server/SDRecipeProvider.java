package com.sofodev.sworddisplay.data.server;

import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public final class SDRecipeProvider extends RecipeProvider {
    private SDRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        for (ModBlocks.BlockRegistryEntry entry : ModBlocks.REGISTRY_LIST) {
            var material = entry.coreMaterial();
            var display = entry.blocks().displayBlock().get();
            var swordCase = entry.blocks().caseBlock().get();
            var wallDisplay = entry.blocks().wallDisplay().get();

            shaped(RecipeCategory.MISC, display)
                    .define('M', material)
                    .define('C', ItemTags.SLABS)
                    .pattern(" M ")
                    .pattern("MCM")
                    .group("sworddisplay:display")
                    .unlockedBy("has_material", has(material))
                    .save(output);

            shaped(RecipeCategory.MISC, swordCase)
                    .define('M', material)
                    .define('C', ItemTags.SLABS)
                    .define('G', Items.GLASS_PANE)
                    .pattern("GGG")
                    .pattern("GMG")
                    .pattern("MCM")
                    .group("sworddisplay:case")
                    .unlockedBy("has_material", has(material))
                    .save(output);

            shaped(RecipeCategory.MISC, wallDisplay)
                    .define('M', material)
                    .define('G', Items.GLASS_PANE)
                    .pattern(" G ")
                    .pattern("GMG")
                    .pattern(" G ")
                    .group("sworddisplay:wall_display")
                    .unlockedBy("has_material", has(material))
                    .save(output);
        }
    }

    public static final class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new SDRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "Sword Displays Recipes";
        }
    }
}
