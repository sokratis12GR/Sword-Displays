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

            String family = getMaterialFamily(entry);

            shaped(RecipeCategory.MISC, display).define('M', material).define('C', ItemTags.SLABS).pattern(" M ").pattern("MCM").group("sworddisplay:display_" + family).unlockedBy("has_material", has(material)).save(output);

            shaped(RecipeCategory.MISC, swordCase).define('M', material).define('C', ItemTags.SLABS).define('G', Items.GLASS_PANE).pattern("GGG").pattern("GMG").pattern("MCM").group("sworddisplay:case_" + family).unlockedBy("has_material", has(material)).save(output);

            if (!entry.key().equals("slime_block")) shaped(RecipeCategory.MISC, wallDisplay).define('M', material).define('G', Items.GLASS_PANE).pattern(" G ").pattern("GMG").pattern(" G ").group("sworddisplay:wall_display_" + family).unlockedBy("has_material", has(material)).save(output);
        }
        for (ModBlocks.StyledBlockEntry entry : ModBlocks.STYLED_ENTRIES) {
            var accent = switch (entry.style()) {
                case "plaque" -> Items.LEATHER;
                case "rack" -> Items.STICK;
                default -> Items.GOLD_NUGGET;
            };
            String group = "sworddisplay:" + entry.style() + "_";
            String family = getMaterialFamily(entry.key());

            shaped(RecipeCategory.MISC, entry.blocks().displayBlock().get())
                    .define('D', entry.originals().displayBlock().get()).define('A', accent)
                    .pattern(" A ").pattern(" D ")
                    .group(group + "display_" + family)
                    .unlockedBy("has_original", has(entry.originals().displayBlock().get())).save(output);
            shaped(RecipeCategory.MISC, entry.blocks().caseBlock().get())
                    .define('D', entry.originals().caseBlock().get()).define('A', accent)
                    .pattern(" A ").pattern(" D ")
                    .group(group + "case_" + family)
                    .unlockedBy("has_original", has(entry.originals().caseBlock().get())).save(output);
            if (!entry.key().equals("slime_block")) {
                shaped(RecipeCategory.MISC, entry.blocks().wallDisplay().get())
                        .define('D', entry.originals().wallDisplay().get()).define('A', accent)
                        .pattern(" A ").pattern(" D ")
                        .group(group + "wall_display_" + family)
                        .unlockedBy("has_original", has(entry.originals().wallDisplay().get())).save(output);
            }
        }
    }

    private static String getMaterialFamily(ModBlocks.BlockRegistryEntry entry) {
        return getMaterialFamily(entry.key());
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
