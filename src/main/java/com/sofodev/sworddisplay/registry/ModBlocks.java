package com.sofodev.sworddisplay.registry;

import com.sofodev.sworddisplay.blocks.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static com.sofodev.sworddisplay.registry.ModItems.ITEMS;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static final Map<String, BlockAndItemEntry> BLOCK_AND_ITEM_MAP = new java.util.LinkedHashMap<>();

    static {
        BLOCK_AND_ITEM_MAP.put("gold", new BlockAndItemEntry(Blocks.GOLD_BLOCK, Items.GOLD_INGOT));
        BLOCK_AND_ITEM_MAP.put("iron", new BlockAndItemEntry(Blocks.IRON_BLOCK, Items.IRON_INGOT));
        BLOCK_AND_ITEM_MAP.put("copper", new BlockAndItemEntry(Blocks.COPPER_BLOCK, Items.COPPER_INGOT));
        BLOCK_AND_ITEM_MAP.put("diamond", new BlockAndItemEntry(Blocks.DIAMOND_BLOCK, Items.DIAMOND));
        BLOCK_AND_ITEM_MAP.put("emerald", new BlockAndItemEntry(Blocks.EMERALD_BLOCK, Items.EMERALD));
        BLOCK_AND_ITEM_MAP.put("amethyst", new BlockAndItemEntry(Blocks.AMETHYST_BLOCK, Items.AMETHYST_SHARD));
        BLOCK_AND_ITEM_MAP.put("obsidian", new BlockAndItemEntry(Blocks.OBSIDIAN, Items.OBSIDIAN));
        BLOCK_AND_ITEM_MAP.put("stone_bricks", new BlockAndItemEntry(Blocks.STONE_BRICKS, Items.STONE_BRICKS));
        BLOCK_AND_ITEM_MAP.put("end_stone_bricks", new BlockAndItemEntry(Blocks.END_STONE_BRICKS, Items.END_STONE));
        BLOCK_AND_ITEM_MAP.put("netherite", new BlockAndItemEntry(Blocks.NETHERITE_BLOCK, Items.NETHERITE_INGOT));
        BLOCK_AND_ITEM_MAP.put("nether_bricks", new BlockAndItemEntry(Blocks.NETHER_BRICKS, Items.NETHER_BRICK));
        BLOCK_AND_ITEM_MAP.put("purpur", new BlockAndItemEntry(Blocks.PURPUR_BLOCK, Items.PURPUR_BLOCK));
        BLOCK_AND_ITEM_MAP.put("quartz", new BlockAndItemEntry(Blocks.QUARTZ_BLOCK, Items.QUARTZ));
        BLOCK_AND_ITEM_MAP.put("lapis", new BlockAndItemEntry(Blocks.LAPIS_BLOCK, Items.LAPIS_LAZULI));
        BLOCK_AND_ITEM_MAP.put("redstone", new BlockAndItemEntry(Blocks.REDSTONE_BLOCK, Items.REDSTONE));
        BLOCK_AND_ITEM_MAP.put("mangrove", new BlockAndItemEntry(Blocks.MANGROVE_PLANKS, Items.MANGROVE_PLANKS));
        BLOCK_AND_ITEM_MAP.put("cherry", new BlockAndItemEntry(Blocks.CHERRY_PLANKS, Items.CHERRY_PLANKS));
        BLOCK_AND_ITEM_MAP.put("crimson", new BlockAndItemEntry(Blocks.CRIMSON_PLANKS, Items.CRIMSON_PLANKS));
        BLOCK_AND_ITEM_MAP.put("oak", new BlockAndItemEntry(Blocks.OAK_PLANKS, Items.OAK_PLANKS));
        BLOCK_AND_ITEM_MAP.put("birch", new BlockAndItemEntry(Blocks.BIRCH_PLANKS, Items.BIRCH_PLANKS));
        BLOCK_AND_ITEM_MAP.put("spruce", new BlockAndItemEntry(Blocks.SPRUCE_PLANKS, Items.SPRUCE_PLANKS));
        BLOCK_AND_ITEM_MAP.put("acacia", new BlockAndItemEntry(Blocks.ACACIA_PLANKS, Items.ACACIA_PLANKS));
        BLOCK_AND_ITEM_MAP.put("jungle", new BlockAndItemEntry(Blocks.JUNGLE_PLANKS, Items.JUNGLE_PLANKS));
        BLOCK_AND_ITEM_MAP.put("dark_oak", new BlockAndItemEntry(Blocks.DARK_OAK_PLANKS, Items.DARK_OAK_PLANKS));
        BLOCK_AND_ITEM_MAP.put("prismarine", new BlockAndItemEntry(Blocks.PRISMARINE, Items.PRISMARINE));
        BLOCK_AND_ITEM_MAP.put("stone", new BlockAndItemEntry(Blocks.STONE, Items.STONE));

        // Additional wood and bamboo (4)
        BLOCK_AND_ITEM_MAP.put("bamboo", new BlockAndItemEntry(Blocks.BAMBOO_PLANKS, Items.BAMBOO_PLANKS));
        BLOCK_AND_ITEM_MAP.put("warped", new BlockAndItemEntry(Blocks.WARPED_PLANKS, Items.WARPED_PLANKS));
        BLOCK_AND_ITEM_MAP.put("bamboo_mosaic", new BlockAndItemEntry(Blocks.BAMBOO_MOSAIC, Items.BAMBOO_MOSAIC));

        // Natural stone and rock (24)
        BLOCK_AND_ITEM_MAP.put("cobblestone", new BlockAndItemEntry(Blocks.COBBLESTONE, Items.COBBLESTONE));
        BLOCK_AND_ITEM_MAP.put("mossy_cobblestone", new BlockAndItemEntry(Blocks.MOSSY_COBBLESTONE, Items.MOSSY_COBBLESTONE));
        BLOCK_AND_ITEM_MAP.put("granite", new BlockAndItemEntry(Blocks.GRANITE, Items.GRANITE));
        BLOCK_AND_ITEM_MAP.put("polished_granite", new BlockAndItemEntry(Blocks.POLISHED_GRANITE, Items.POLISHED_GRANITE));
        BLOCK_AND_ITEM_MAP.put("diorite", new BlockAndItemEntry(Blocks.DIORITE, Items.DIORITE));
        BLOCK_AND_ITEM_MAP.put("polished_diorite", new BlockAndItemEntry(Blocks.POLISHED_DIORITE, Items.POLISHED_DIORITE));
        BLOCK_AND_ITEM_MAP.put("andesite", new BlockAndItemEntry(Blocks.ANDESITE, Items.ANDESITE));
        BLOCK_AND_ITEM_MAP.put("polished_andesite", new BlockAndItemEntry(Blocks.POLISHED_ANDESITE, Items.POLISHED_ANDESITE));
        BLOCK_AND_ITEM_MAP.put("deepslate", new BlockAndItemEntry(Blocks.DEEPSLATE, Items.DEEPSLATE));
        BLOCK_AND_ITEM_MAP.put("cobbled_deepslate", new BlockAndItemEntry(Blocks.COBBLED_DEEPSLATE, Items.COBBLED_DEEPSLATE));
        BLOCK_AND_ITEM_MAP.put("polished_deepslate", new BlockAndItemEntry(Blocks.POLISHED_DEEPSLATE, Items.POLISHED_DEEPSLATE));
        BLOCK_AND_ITEM_MAP.put("chiseled_deepslate", new BlockAndItemEntry(Blocks.CHISELED_DEEPSLATE, Items.CHISELED_DEEPSLATE));
        BLOCK_AND_ITEM_MAP.put("deepslate_bricks", new BlockAndItemEntry(Blocks.DEEPSLATE_BRICKS, Items.DEEPSLATE_BRICKS));
        BLOCK_AND_ITEM_MAP.put("cracked_deepslate_bricks", new BlockAndItemEntry(Blocks.CRACKED_DEEPSLATE_BRICKS, Items.CRACKED_DEEPSLATE_BRICKS));
        BLOCK_AND_ITEM_MAP.put("deepslate_tiles", new BlockAndItemEntry(Blocks.DEEPSLATE_TILES, Items.DEEPSLATE_TILES));
        BLOCK_AND_ITEM_MAP.put("cracked_deepslate_tiles", new BlockAndItemEntry(Blocks.CRACKED_DEEPSLATE_TILES, Items.CRACKED_DEEPSLATE_TILES));
        BLOCK_AND_ITEM_MAP.put("tuff", new BlockAndItemEntry(Blocks.TUFF, Items.TUFF));
        BLOCK_AND_ITEM_MAP.put("polished_tuff", new BlockAndItemEntry(Blocks.POLISHED_TUFF, Items.POLISHED_TUFF));
        BLOCK_AND_ITEM_MAP.put("chiseled_tuff", new BlockAndItemEntry(Blocks.CHISELED_TUFF, Items.CHISELED_TUFF));
        BLOCK_AND_ITEM_MAP.put("tuff_bricks", new BlockAndItemEntry(Blocks.TUFF_BRICKS, Items.TUFF_BRICKS));
        BLOCK_AND_ITEM_MAP.put("chiseled_tuff_bricks", new BlockAndItemEntry(Blocks.CHISELED_TUFF_BRICKS, Items.CHISELED_TUFF_BRICKS));
        BLOCK_AND_ITEM_MAP.put("calcite", new BlockAndItemEntry(Blocks.CALCITE, Items.CALCITE));
        BLOCK_AND_ITEM_MAP.put("dripstone_block", new BlockAndItemEntry(Blocks.DRIPSTONE_BLOCK, Items.DRIPSTONE_BLOCK));
        BLOCK_AND_ITEM_MAP.put("smooth_stone", new BlockAndItemEntry(Blocks.SMOOTH_STONE, Items.SMOOTH_STONE));

        // Bricks and masonry (12)
        BLOCK_AND_ITEM_MAP.put("bricks", new BlockAndItemEntry(Blocks.BRICKS, Items.BRICKS));
        BLOCK_AND_ITEM_MAP.put("mossy_stone_bricks", new BlockAndItemEntry(Blocks.MOSSY_STONE_BRICKS, Items.MOSSY_STONE_BRICKS));
        BLOCK_AND_ITEM_MAP.put("cracked_stone_bricks", new BlockAndItemEntry(Blocks.CRACKED_STONE_BRICKS, Items.CRACKED_STONE_BRICKS));
        BLOCK_AND_ITEM_MAP.put("chiseled_stone_bricks", new BlockAndItemEntry(Blocks.CHISELED_STONE_BRICKS, Items.CHISELED_STONE_BRICKS));
        BLOCK_AND_ITEM_MAP.put("mud_bricks", new BlockAndItemEntry(Blocks.MUD_BRICKS, Items.MUD_BRICKS));
        BLOCK_AND_ITEM_MAP.put("packed_mud", new BlockAndItemEntry(Blocks.PACKED_MUD, Items.PACKED_MUD));
        BLOCK_AND_ITEM_MAP.put("red_nether_bricks", new BlockAndItemEntry(Blocks.RED_NETHER_BRICKS, Items.RED_NETHER_BRICKS));
        BLOCK_AND_ITEM_MAP.put("cracked_nether_bricks", new BlockAndItemEntry(Blocks.CRACKED_NETHER_BRICKS, Items.CRACKED_NETHER_BRICKS));
        BLOCK_AND_ITEM_MAP.put("chiseled_nether_bricks", new BlockAndItemEntry(Blocks.CHISELED_NETHER_BRICKS, Items.CHISELED_NETHER_BRICKS));
        BLOCK_AND_ITEM_MAP.put("end_stone", new BlockAndItemEntry(Blocks.END_STONE, Items.END_STONE));

        // Sandstone and quartz (9)
        BLOCK_AND_ITEM_MAP.put("sandstone", new BlockAndItemEntry(Blocks.SANDSTONE, Items.SANDSTONE));
        BLOCK_AND_ITEM_MAP.put("cut_sandstone", new BlockAndItemEntry(Blocks.CUT_SANDSTONE, Items.CUT_SANDSTONE));
        BLOCK_AND_ITEM_MAP.put("chiseled_sandstone", new BlockAndItemEntry(Blocks.CHISELED_SANDSTONE, Items.CHISELED_SANDSTONE));
        BLOCK_AND_ITEM_MAP.put("red_sandstone", new BlockAndItemEntry(Blocks.RED_SANDSTONE, Items.RED_SANDSTONE));
        BLOCK_AND_ITEM_MAP.put("cut_red_sandstone", new BlockAndItemEntry(Blocks.CUT_RED_SANDSTONE, Items.CUT_RED_SANDSTONE));
        BLOCK_AND_ITEM_MAP.put("chiseled_red_sandstone", new BlockAndItemEntry(Blocks.CHISELED_RED_SANDSTONE, Items.CHISELED_RED_SANDSTONE));
        BLOCK_AND_ITEM_MAP.put("chiseled_quartz_block", new BlockAndItemEntry(Blocks.CHISELED_QUARTZ_BLOCK, Items.CHISELED_QUARTZ_BLOCK));
        BLOCK_AND_ITEM_MAP.put("quartz_bricks", new BlockAndItemEntry(Blocks.QUARTZ_BRICKS, Items.QUARTZ_BRICKS));
        BLOCK_AND_ITEM_MAP.put("quartz_pillar", new BlockAndItemEntry(Blocks.QUARTZ_PILLAR, Items.QUARTZ_PILLAR));

        // Natural earth (8)
        BLOCK_AND_ITEM_MAP.put("dirt", new BlockAndItemEntry(Blocks.DIRT, Items.DIRT));
        BLOCK_AND_ITEM_MAP.put("coarse_dirt", new BlockAndItemEntry(Blocks.COARSE_DIRT, Items.COARSE_DIRT));
        BLOCK_AND_ITEM_MAP.put("rooted_dirt", new BlockAndItemEntry(Blocks.ROOTED_DIRT, Items.ROOTED_DIRT));
        BLOCK_AND_ITEM_MAP.put("mud", new BlockAndItemEntry(Blocks.MUD, Items.MUD));
        BLOCK_AND_ITEM_MAP.put("clay", new BlockAndItemEntry(Blocks.CLAY, Items.CLAY));
        BLOCK_AND_ITEM_MAP.put("gravel", new BlockAndItemEntry(Blocks.GRAVEL, Items.GRAVEL));
        BLOCK_AND_ITEM_MAP.put("sand", new BlockAndItemEntry(Blocks.SAND, Items.SAND));
        BLOCK_AND_ITEM_MAP.put("red_sand", new BlockAndItemEntry(Blocks.RED_SAND, Items.RED_SAND));

        // Ores and raw resources (22)
        BLOCK_AND_ITEM_MAP.put("coal_block", new BlockAndItemEntry(Blocks.COAL_BLOCK, Items.COAL_BLOCK));
        BLOCK_AND_ITEM_MAP.put("raw_iron_block", new BlockAndItemEntry(Blocks.RAW_IRON_BLOCK, Items.RAW_IRON_BLOCK));
        BLOCK_AND_ITEM_MAP.put("raw_copper_block", new BlockAndItemEntry(Blocks.RAW_COPPER_BLOCK, Items.RAW_COPPER_BLOCK));
        BLOCK_AND_ITEM_MAP.put("raw_gold_block", new BlockAndItemEntry(Blocks.RAW_GOLD_BLOCK, Items.RAW_GOLD_BLOCK));
        BLOCK_AND_ITEM_MAP.put("coal_ore", new BlockAndItemEntry(Blocks.COAL_ORE, Items.COAL_ORE));
        BLOCK_AND_ITEM_MAP.put("deepslate_coal_ore", new BlockAndItemEntry(Blocks.DEEPSLATE_COAL_ORE, Items.DEEPSLATE_COAL_ORE));
        BLOCK_AND_ITEM_MAP.put("iron_ore", new BlockAndItemEntry(Blocks.IRON_ORE, Items.IRON_ORE));
        BLOCK_AND_ITEM_MAP.put("deepslate_iron_ore", new BlockAndItemEntry(Blocks.DEEPSLATE_IRON_ORE, Items.DEEPSLATE_IRON_ORE));
        BLOCK_AND_ITEM_MAP.put("copper_ore", new BlockAndItemEntry(Blocks.COPPER_ORE, Items.COPPER_ORE));
        BLOCK_AND_ITEM_MAP.put("deepslate_copper_ore", new BlockAndItemEntry(Blocks.DEEPSLATE_COPPER_ORE, Items.DEEPSLATE_COPPER_ORE));
        BLOCK_AND_ITEM_MAP.put("gold_ore", new BlockAndItemEntry(Blocks.GOLD_ORE, Items.GOLD_ORE));
        BLOCK_AND_ITEM_MAP.put("deepslate_gold_ore", new BlockAndItemEntry(Blocks.DEEPSLATE_GOLD_ORE, Items.DEEPSLATE_GOLD_ORE));
        BLOCK_AND_ITEM_MAP.put("redstone_ore", new BlockAndItemEntry(Blocks.REDSTONE_ORE, Items.REDSTONE_ORE));
        BLOCK_AND_ITEM_MAP.put("deepslate_redstone_ore", new BlockAndItemEntry(Blocks.DEEPSLATE_REDSTONE_ORE, Items.DEEPSLATE_REDSTONE_ORE));
        BLOCK_AND_ITEM_MAP.put("lapis_ore", new BlockAndItemEntry(Blocks.LAPIS_ORE, Items.LAPIS_ORE));
        BLOCK_AND_ITEM_MAP.put("deepslate_lapis_ore", new BlockAndItemEntry(Blocks.DEEPSLATE_LAPIS_ORE, Items.DEEPSLATE_LAPIS_ORE));
        BLOCK_AND_ITEM_MAP.put("diamond_ore", new BlockAndItemEntry(Blocks.DIAMOND_ORE, Items.DIAMOND_ORE));
        BLOCK_AND_ITEM_MAP.put("deepslate_diamond_ore", new BlockAndItemEntry(Blocks.DEEPSLATE_DIAMOND_ORE, Items.DEEPSLATE_DIAMOND_ORE));
        BLOCK_AND_ITEM_MAP.put("emerald_ore", new BlockAndItemEntry(Blocks.EMERALD_ORE, Items.EMERALD_ORE));
        BLOCK_AND_ITEM_MAP.put("deepslate_emerald_ore", new BlockAndItemEntry(Blocks.DEEPSLATE_EMERALD_ORE, Items.DEEPSLATE_EMERALD_ORE));
        BLOCK_AND_ITEM_MAP.put("nether_gold_ore", new BlockAndItemEntry(Blocks.NETHER_GOLD_ORE, Items.NETHER_GOLD_ORE));
        BLOCK_AND_ITEM_MAP.put("nether_quartz_ore", new BlockAndItemEntry(Blocks.NETHER_QUARTZ_ORE, Items.NETHER_QUARTZ_ORE));

        // Copper variants (11)
        BLOCK_AND_ITEM_MAP.put("exposed_copper", new BlockAndItemEntry(Blocks.EXPOSED_COPPER, Items.EXPOSED_COPPER));
        BLOCK_AND_ITEM_MAP.put("weathered_copper", new BlockAndItemEntry(Blocks.WEATHERED_COPPER, Items.WEATHERED_COPPER));
        BLOCK_AND_ITEM_MAP.put("oxidized_copper", new BlockAndItemEntry(Blocks.OXIDIZED_COPPER, Items.OXIDIZED_COPPER));
        BLOCK_AND_ITEM_MAP.put("cut_copper", new BlockAndItemEntry(Blocks.CUT_COPPER, Items.CUT_COPPER));
        BLOCK_AND_ITEM_MAP.put("exposed_cut_copper", new BlockAndItemEntry(Blocks.EXPOSED_CUT_COPPER, Items.EXPOSED_CUT_COPPER));
        BLOCK_AND_ITEM_MAP.put("weathered_cut_copper", new BlockAndItemEntry(Blocks.WEATHERED_CUT_COPPER, Items.WEATHERED_CUT_COPPER));
        BLOCK_AND_ITEM_MAP.put("oxidized_cut_copper", new BlockAndItemEntry(Blocks.OXIDIZED_CUT_COPPER, Items.OXIDIZED_CUT_COPPER));
        BLOCK_AND_ITEM_MAP.put("chiseled_copper", new BlockAndItemEntry(Blocks.CHISELED_COPPER, Items.CHISELED_COPPER));
        BLOCK_AND_ITEM_MAP.put("exposed_chiseled_copper", new BlockAndItemEntry(Blocks.EXPOSED_CHISELED_COPPER, Items.EXPOSED_CHISELED_COPPER));
        BLOCK_AND_ITEM_MAP.put("weathered_chiseled_copper", new BlockAndItemEntry(Blocks.WEATHERED_CHISELED_COPPER, Items.WEATHERED_CHISELED_COPPER));
        BLOCK_AND_ITEM_MAP.put("oxidized_chiseled_copper", new BlockAndItemEntry(Blocks.OXIDIZED_CHISELED_COPPER, Items.OXIDIZED_CHISELED_COPPER));

        // Nether and End (16)
        BLOCK_AND_ITEM_MAP.put("netherrack", new BlockAndItemEntry(Blocks.NETHERRACK, Items.NETHERRACK));
        BLOCK_AND_ITEM_MAP.put("soul_sand", new BlockAndItemEntry(Blocks.SOUL_SAND, Items.SOUL_SAND));
        BLOCK_AND_ITEM_MAP.put("soul_soil", new BlockAndItemEntry(Blocks.SOUL_SOIL, Items.SOUL_SOIL));
        BLOCK_AND_ITEM_MAP.put("smooth_basalt", new BlockAndItemEntry(Blocks.SMOOTH_BASALT, Items.SMOOTH_BASALT));
        BLOCK_AND_ITEM_MAP.put("blackstone", new BlockAndItemEntry(Blocks.BLACKSTONE, Items.BLACKSTONE));
        BLOCK_AND_ITEM_MAP.put("polished_blackstone", new BlockAndItemEntry(Blocks.POLISHED_BLACKSTONE, Items.POLISHED_BLACKSTONE));
        BLOCK_AND_ITEM_MAP.put("chiseled_polished_blackstone", new BlockAndItemEntry(Blocks.CHISELED_POLISHED_BLACKSTONE, Items.CHISELED_POLISHED_BLACKSTONE));
        BLOCK_AND_ITEM_MAP.put("polished_blackstone_bricks", new BlockAndItemEntry(Blocks.POLISHED_BLACKSTONE_BRICKS, Items.POLISHED_BLACKSTONE_BRICKS));
        BLOCK_AND_ITEM_MAP.put("cracked_polished_blackstone_bricks", new BlockAndItemEntry(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, Items.CRACKED_POLISHED_BLACKSTONE_BRICKS));
        BLOCK_AND_ITEM_MAP.put("gilded_blackstone", new BlockAndItemEntry(Blocks.GILDED_BLACKSTONE, Items.GILDED_BLACKSTONE));
        BLOCK_AND_ITEM_MAP.put("crimson_nylium", new BlockAndItemEntry(Blocks.CRIMSON_NYLIUM, Items.CRIMSON_NYLIUM));
        BLOCK_AND_ITEM_MAP.put("warped_nylium", new BlockAndItemEntry(Blocks.WARPED_NYLIUM, Items.WARPED_NYLIUM));
        BLOCK_AND_ITEM_MAP.put("nether_wart_block", new BlockAndItemEntry(Blocks.NETHER_WART_BLOCK, Items.NETHER_WART_BLOCK));
        BLOCK_AND_ITEM_MAP.put("warped_wart_block", new BlockAndItemEntry(Blocks.WARPED_WART_BLOCK, Items.WARPED_WART_BLOCK));
        BLOCK_AND_ITEM_MAP.put("shroomlight", new BlockAndItemEntry(Blocks.SHROOMLIGHT, Items.SHROOMLIGHT));
        BLOCK_AND_ITEM_MAP.put("purpur_pillar", new BlockAndItemEntry(Blocks.PURPUR_PILLAR, Items.PURPUR_PILLAR));

        // Ocean and underground (8)
        BLOCK_AND_ITEM_MAP.put("prismarine_bricks", new BlockAndItemEntry(Blocks.PRISMARINE_BRICKS, Items.PRISMARINE_BRICKS));
        BLOCK_AND_ITEM_MAP.put("dark_prismarine", new BlockAndItemEntry(Blocks.DARK_PRISMARINE, Items.DARK_PRISMARINE));
        BLOCK_AND_ITEM_MAP.put("sea_lantern", new BlockAndItemEntry(Blocks.SEA_LANTERN, Items.SEA_LANTERN));
        BLOCK_AND_ITEM_MAP.put("sponge", new BlockAndItemEntry(Blocks.SPONGE, Items.SPONGE));
        BLOCK_AND_ITEM_MAP.put("wet_sponge", new BlockAndItemEntry(Blocks.WET_SPONGE, Items.WET_SPONGE));
        BLOCK_AND_ITEM_MAP.put("moss_block", new BlockAndItemEntry(Blocks.MOSS_BLOCK, Items.MOSS_BLOCK));
        BLOCK_AND_ITEM_MAP.put("sculk", new BlockAndItemEntry(Blocks.SCULK, Items.SCULK));

        // Glass and lighting (4)
        BLOCK_AND_ITEM_MAP.put("glass", new BlockAndItemEntry(Blocks.GLASS, Items.GLASS));
        BLOCK_AND_ITEM_MAP.put("tinted_glass", new BlockAndItemEntry(Blocks.TINTED_GLASS, Items.TINTED_GLASS));
        BLOCK_AND_ITEM_MAP.put("glowstone", new BlockAndItemEntry(Blocks.GLOWSTONE, Items.GLOWSTONE));
        BLOCK_AND_ITEM_MAP.put("redstone_lamp", new BlockAndItemEntry(Blocks.REDSTONE_LAMP, Items.REDSTONE_LAMP));

        // Miscellaneous decorative (4)
        BLOCK_AND_ITEM_MAP.put("bookshelf", new BlockAndItemEntry(Blocks.BOOKSHELF, Items.BOOKSHELF));
        BLOCK_AND_ITEM_MAP.put("honeycomb_block", new BlockAndItemEntry(Blocks.HONEYCOMB_BLOCK, Items.HONEYCOMB_BLOCK));
        BLOCK_AND_ITEM_MAP.put("slime_block", new BlockAndItemEntry(Blocks.SLIME_BLOCK, Items.SLIME_BLOCK));

        // Wool (16)
        BLOCK_AND_ITEM_MAP.put("white_wool", new BlockAndItemEntry(Blocks.WHITE_WOOL, Items.WHITE_WOOL));
        BLOCK_AND_ITEM_MAP.put("orange_wool", new BlockAndItemEntry(Blocks.ORANGE_WOOL, Items.ORANGE_WOOL));
        BLOCK_AND_ITEM_MAP.put("magenta_wool", new BlockAndItemEntry(Blocks.MAGENTA_WOOL, Items.MAGENTA_WOOL));
        BLOCK_AND_ITEM_MAP.put("light_blue_wool", new BlockAndItemEntry(Blocks.LIGHT_BLUE_WOOL, Items.LIGHT_BLUE_WOOL));
        BLOCK_AND_ITEM_MAP.put("yellow_wool", new BlockAndItemEntry(Blocks.YELLOW_WOOL, Items.YELLOW_WOOL));
        BLOCK_AND_ITEM_MAP.put("lime_wool", new BlockAndItemEntry(Blocks.LIME_WOOL, Items.LIME_WOOL));
        BLOCK_AND_ITEM_MAP.put("pink_wool", new BlockAndItemEntry(Blocks.PINK_WOOL, Items.PINK_WOOL));
        BLOCK_AND_ITEM_MAP.put("gray_wool", new BlockAndItemEntry(Blocks.GRAY_WOOL, Items.GRAY_WOOL));
        BLOCK_AND_ITEM_MAP.put("light_gray_wool", new BlockAndItemEntry(Blocks.LIGHT_GRAY_WOOL, Items.LIGHT_GRAY_WOOL));
        BLOCK_AND_ITEM_MAP.put("cyan_wool", new BlockAndItemEntry(Blocks.CYAN_WOOL, Items.CYAN_WOOL));
        BLOCK_AND_ITEM_MAP.put("purple_wool", new BlockAndItemEntry(Blocks.PURPLE_WOOL, Items.PURPLE_WOOL));
        BLOCK_AND_ITEM_MAP.put("blue_wool", new BlockAndItemEntry(Blocks.BLUE_WOOL, Items.BLUE_WOOL));
        BLOCK_AND_ITEM_MAP.put("brown_wool", new BlockAndItemEntry(Blocks.BROWN_WOOL, Items.BROWN_WOOL));
        BLOCK_AND_ITEM_MAP.put("green_wool", new BlockAndItemEntry(Blocks.GREEN_WOOL, Items.GREEN_WOOL));
        BLOCK_AND_ITEM_MAP.put("red_wool", new BlockAndItemEntry(Blocks.RED_WOOL, Items.RED_WOOL));
        BLOCK_AND_ITEM_MAP.put("black_wool", new BlockAndItemEntry(Blocks.BLACK_WOOL, Items.BLACK_WOOL));

        // Terracotta (16)
        BLOCK_AND_ITEM_MAP.put("white_terracotta", new BlockAndItemEntry(Blocks.WHITE_TERRACOTTA, Items.WHITE_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("orange_terracotta", new BlockAndItemEntry(Blocks.ORANGE_TERRACOTTA, Items.ORANGE_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("magenta_terracotta", new BlockAndItemEntry(Blocks.MAGENTA_TERRACOTTA, Items.MAGENTA_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("light_blue_terracotta", new BlockAndItemEntry(Blocks.LIGHT_BLUE_TERRACOTTA, Items.LIGHT_BLUE_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("yellow_terracotta", new BlockAndItemEntry(Blocks.YELLOW_TERRACOTTA, Items.YELLOW_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("lime_terracotta", new BlockAndItemEntry(Blocks.LIME_TERRACOTTA, Items.LIME_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("pink_terracotta", new BlockAndItemEntry(Blocks.PINK_TERRACOTTA, Items.PINK_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("gray_terracotta", new BlockAndItemEntry(Blocks.GRAY_TERRACOTTA, Items.GRAY_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("light_gray_terracotta", new BlockAndItemEntry(Blocks.LIGHT_GRAY_TERRACOTTA, Items.LIGHT_GRAY_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("cyan_terracotta", new BlockAndItemEntry(Blocks.CYAN_TERRACOTTA, Items.CYAN_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("purple_terracotta", new BlockAndItemEntry(Blocks.PURPLE_TERRACOTTA, Items.PURPLE_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("blue_terracotta", new BlockAndItemEntry(Blocks.BLUE_TERRACOTTA, Items.BLUE_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("brown_terracotta", new BlockAndItemEntry(Blocks.BROWN_TERRACOTTA, Items.BROWN_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("green_terracotta", new BlockAndItemEntry(Blocks.GREEN_TERRACOTTA, Items.GREEN_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("red_terracotta", new BlockAndItemEntry(Blocks.RED_TERRACOTTA, Items.RED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("black_terracotta", new BlockAndItemEntry(Blocks.BLACK_TERRACOTTA, Items.BLACK_TERRACOTTA));

        // Concrete (16)
        BLOCK_AND_ITEM_MAP.put("white_concrete", new BlockAndItemEntry(Blocks.WHITE_CONCRETE, Items.WHITE_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("orange_concrete", new BlockAndItemEntry(Blocks.ORANGE_CONCRETE, Items.ORANGE_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("magenta_concrete", new BlockAndItemEntry(Blocks.MAGENTA_CONCRETE, Items.MAGENTA_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("light_blue_concrete", new BlockAndItemEntry(Blocks.LIGHT_BLUE_CONCRETE, Items.LIGHT_BLUE_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("yellow_concrete", new BlockAndItemEntry(Blocks.YELLOW_CONCRETE, Items.YELLOW_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("lime_concrete", new BlockAndItemEntry(Blocks.LIME_CONCRETE, Items.LIME_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("pink_concrete", new BlockAndItemEntry(Blocks.PINK_CONCRETE, Items.PINK_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("gray_concrete", new BlockAndItemEntry(Blocks.GRAY_CONCRETE, Items.GRAY_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("light_gray_concrete", new BlockAndItemEntry(Blocks.LIGHT_GRAY_CONCRETE, Items.LIGHT_GRAY_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("cyan_concrete", new BlockAndItemEntry(Blocks.CYAN_CONCRETE, Items.CYAN_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("purple_concrete", new BlockAndItemEntry(Blocks.PURPLE_CONCRETE, Items.PURPLE_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("blue_concrete", new BlockAndItemEntry(Blocks.BLUE_CONCRETE, Items.BLUE_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("brown_concrete", new BlockAndItemEntry(Blocks.BROWN_CONCRETE, Items.BROWN_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("green_concrete", new BlockAndItemEntry(Blocks.GREEN_CONCRETE, Items.GREEN_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("red_concrete", new BlockAndItemEntry(Blocks.RED_CONCRETE, Items.RED_CONCRETE));
        BLOCK_AND_ITEM_MAP.put("black_concrete", new BlockAndItemEntry(Blocks.BLACK_CONCRETE, Items.BLACK_CONCRETE));

        // Concrete powder (16)
        BLOCK_AND_ITEM_MAP.put("white_concrete_powder", new BlockAndItemEntry(Blocks.WHITE_CONCRETE_POWDER, Items.WHITE_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("orange_concrete_powder", new BlockAndItemEntry(Blocks.ORANGE_CONCRETE_POWDER, Items.ORANGE_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("magenta_concrete_powder", new BlockAndItemEntry(Blocks.MAGENTA_CONCRETE_POWDER, Items.MAGENTA_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("light_blue_concrete_powder", new BlockAndItemEntry(Blocks.LIGHT_BLUE_CONCRETE_POWDER, Items.LIGHT_BLUE_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("yellow_concrete_powder", new BlockAndItemEntry(Blocks.YELLOW_CONCRETE_POWDER, Items.YELLOW_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("lime_concrete_powder", new BlockAndItemEntry(Blocks.LIME_CONCRETE_POWDER, Items.LIME_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("pink_concrete_powder", new BlockAndItemEntry(Blocks.PINK_CONCRETE_POWDER, Items.PINK_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("gray_concrete_powder", new BlockAndItemEntry(Blocks.GRAY_CONCRETE_POWDER, Items.GRAY_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("light_gray_concrete_powder", new BlockAndItemEntry(Blocks.LIGHT_GRAY_CONCRETE_POWDER, Items.LIGHT_GRAY_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("cyan_concrete_powder", new BlockAndItemEntry(Blocks.CYAN_CONCRETE_POWDER, Items.CYAN_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("purple_concrete_powder", new BlockAndItemEntry(Blocks.PURPLE_CONCRETE_POWDER, Items.PURPLE_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("blue_concrete_powder", new BlockAndItemEntry(Blocks.BLUE_CONCRETE_POWDER, Items.BLUE_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("brown_concrete_powder", new BlockAndItemEntry(Blocks.BROWN_CONCRETE_POWDER, Items.BROWN_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("green_concrete_powder", new BlockAndItemEntry(Blocks.GREEN_CONCRETE_POWDER, Items.GREEN_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("red_concrete_powder", new BlockAndItemEntry(Blocks.RED_CONCRETE_POWDER, Items.RED_CONCRETE_POWDER));
        BLOCK_AND_ITEM_MAP.put("black_concrete_powder", new BlockAndItemEntry(Blocks.BLACK_CONCRETE_POWDER, Items.BLACK_CONCRETE_POWDER));

        // Stained glass (16)
        BLOCK_AND_ITEM_MAP.put("white_stained_glass", new BlockAndItemEntry(Blocks.WHITE_STAINED_GLASS, Items.WHITE_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("orange_stained_glass", new BlockAndItemEntry(Blocks.ORANGE_STAINED_GLASS, Items.ORANGE_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("magenta_stained_glass", new BlockAndItemEntry(Blocks.MAGENTA_STAINED_GLASS, Items.MAGENTA_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("light_blue_stained_glass", new BlockAndItemEntry(Blocks.LIGHT_BLUE_STAINED_GLASS, Items.LIGHT_BLUE_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("yellow_stained_glass", new BlockAndItemEntry(Blocks.YELLOW_STAINED_GLASS, Items.YELLOW_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("lime_stained_glass", new BlockAndItemEntry(Blocks.LIME_STAINED_GLASS, Items.LIME_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("pink_stained_glass", new BlockAndItemEntry(Blocks.PINK_STAINED_GLASS, Items.PINK_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("gray_stained_glass", new BlockAndItemEntry(Blocks.GRAY_STAINED_GLASS, Items.GRAY_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("light_gray_stained_glass", new BlockAndItemEntry(Blocks.LIGHT_GRAY_STAINED_GLASS, Items.LIGHT_GRAY_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("cyan_stained_glass", new BlockAndItemEntry(Blocks.CYAN_STAINED_GLASS, Items.CYAN_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("purple_stained_glass", new BlockAndItemEntry(Blocks.PURPLE_STAINED_GLASS, Items.PURPLE_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("blue_stained_glass", new BlockAndItemEntry(Blocks.BLUE_STAINED_GLASS, Items.BLUE_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("brown_stained_glass", new BlockAndItemEntry(Blocks.BROWN_STAINED_GLASS, Items.BROWN_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("green_stained_glass", new BlockAndItemEntry(Blocks.GREEN_STAINED_GLASS, Items.GREEN_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("red_stained_glass", new BlockAndItemEntry(Blocks.RED_STAINED_GLASS, Items.RED_STAINED_GLASS));
        BLOCK_AND_ITEM_MAP.put("black_stained_glass", new BlockAndItemEntry(Blocks.BLACK_STAINED_GLASS, Items.BLACK_STAINED_GLASS));

        // Glazed terracotta (16)
        BLOCK_AND_ITEM_MAP.put("white_glazed_terracotta", new BlockAndItemEntry(Blocks.WHITE_GLAZED_TERRACOTTA, Items.WHITE_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("orange_glazed_terracotta", new BlockAndItemEntry(Blocks.ORANGE_GLAZED_TERRACOTTA, Items.ORANGE_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("magenta_glazed_terracotta", new BlockAndItemEntry(Blocks.MAGENTA_GLAZED_TERRACOTTA, Items.MAGENTA_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("light_blue_glazed_terracotta", new BlockAndItemEntry(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Items.LIGHT_BLUE_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("yellow_glazed_terracotta", new BlockAndItemEntry(Blocks.YELLOW_GLAZED_TERRACOTTA, Items.YELLOW_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("lime_glazed_terracotta", new BlockAndItemEntry(Blocks.LIME_GLAZED_TERRACOTTA, Items.LIME_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("pink_glazed_terracotta", new BlockAndItemEntry(Blocks.PINK_GLAZED_TERRACOTTA, Items.PINK_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("gray_glazed_terracotta", new BlockAndItemEntry(Blocks.GRAY_GLAZED_TERRACOTTA, Items.GRAY_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("light_gray_glazed_terracotta", new BlockAndItemEntry(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, Items.LIGHT_GRAY_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("cyan_glazed_terracotta", new BlockAndItemEntry(Blocks.CYAN_GLAZED_TERRACOTTA, Items.CYAN_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("purple_glazed_terracotta", new BlockAndItemEntry(Blocks.PURPLE_GLAZED_TERRACOTTA, Items.PURPLE_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("blue_glazed_terracotta", new BlockAndItemEntry(Blocks.BLUE_GLAZED_TERRACOTTA, Items.BLUE_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("brown_glazed_terracotta", new BlockAndItemEntry(Blocks.BROWN_GLAZED_TERRACOTTA, Items.BROWN_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("green_glazed_terracotta", new BlockAndItemEntry(Blocks.GREEN_GLAZED_TERRACOTTA, Items.GREEN_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("red_glazed_terracotta", new BlockAndItemEntry(Blocks.RED_GLAZED_TERRACOTTA, Items.RED_GLAZED_TERRACOTTA));
        BLOCK_AND_ITEM_MAP.put("black_glazed_terracotta", new BlockAndItemEntry(Blocks.BLACK_GLAZED_TERRACOTTA, Items.BLACK_GLAZED_TERRACOTTA));
    }


    // List of all registry entries
    public static final List<BlockRegistryEntry> REGISTRY_LIST = new ArrayList<>();
    public static final RegistryHelper registryHelper = new RegistryHelper(REGISTRY_LIST);

    static {
        registerAll();
    }

    public static void registerAll() {
        BLOCK_AND_ITEM_MAP.forEach((baseName, entry) -> {

            RegistryObject<Block> display = registerBlockWithItem(
                    baseName + "_sword_display",
                    () -> new SwordDisplayBlock(Block.Properties.ofFullCopy(Blocks.STONE))
            );

            RegistryObject<Block> swordCase = registerBlockWithItem(
                    baseName + "_sword_case",
                    () -> new SwordCaseBlock(Block.Properties.ofFullCopy(Blocks.STONE))
            );

            RegistryObject<Block> wallDisplay = registerBlockWithItem(
                    baseName + "_wall_display",
                    () -> new SwordWallDisplayBlock(Block.Properties.ofFullCopy(Blocks.STONE))
            );

            REGISTRY_LIST.add(new BlockRegistryEntry(baseName, new BlockDisplays(display, swordCase, wallDisplay), entry.item(), entry.isWooden()));
        });
    }

    public static String getBaseName(Block block) {
        String name = ForgeRegistries.BLOCKS.getKey(block).getPath();
        return name.replace("_block", "").replace("_planks", "");
    }

    public static String getBaseNameWithoutDisplay(Block block) {
        return getBaseName(block).replace("_sword_display", "").replace("_sword_case", "");
    }

    public static final RegistryObject<BlockEntityType<SwordDisplayTile>> SWORD_DISPLAY_TYPE = TILE_ENTITIES.register("sword_display",
            () -> build(BlockEntityType.Builder.of(
                    SwordDisplayTile::new,
                    REGISTRY_LIST.stream()
                            .flatMap(entry ->
                                    Stream.of(entry.blocks().displayBlock().get(),
                                            entry.blocks().caseBlock().get(),
                                            entry.blocks().wallDisplay().get()))
                            .toArray(Block[]::new)
            )));

    private static <T extends BlockEntity> BlockEntityType<T> build(BlockEntityType.Builder<T> builder) {
        return builder.build(null);
    }

    public static <BLOCK extends Block> RegistryObject<BLOCK> registerBlockWithItem(String name, Supplier<BLOCK> blockSupplier, Function<BLOCK, Item> itemFactory) {
        RegistryObject<BLOCK> block = BLOCKS.register(name, blockSupplier);
        ITEMS.register(name, () -> itemFactory.apply(block.get()));
        return block;
    }

    public static <BLOCK extends Block> RegistryObject<BLOCK> registerBlockWithItem(String name, Supplier<BLOCK> blockSupplier) {
        return registerBlockWithItem(name, blockSupplier, SDBlockItem::new);
    }

    public record BlockDisplays(RegistryObject<Block> displayBlock, RegistryObject<Block> caseBlock,
                                RegistryObject<Block> wallDisplay){}

    public record BlockRegistryEntry(String key, BlockDisplays blocks,
                                     ItemLike coreMaterial, boolean isWooden) {
    }

    public record BlockAndItemEntry(Block block, Item item) {

        public boolean isWooden() {
            return ForgeRegistries.BLOCKS.getKey(block).getPath().contains("plank");
        }
    }
}
