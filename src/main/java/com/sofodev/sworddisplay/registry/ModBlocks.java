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

    public static final Map<String, BlockAndItemEntry> BLOCK_AND_ITEM_MAP = new HashMap<>();

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
                    () -> new SwordDisplayBlock(Block.Properties.copy(Blocks.STONE))
            );

            RegistryObject<Block> swordCase = registerBlockWithItem(
                    baseName + "_sword_case",
                    () -> new SwordCaseBlock(Block.Properties.copy(Blocks.STONE))
            );

            RegistryObject<Block> wallDisplay = registerBlockWithItem(
                    baseName + "_wall_display",
                    () -> new SwordWallDisplayBlock(Block.Properties.copy(Blocks.STONE))
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