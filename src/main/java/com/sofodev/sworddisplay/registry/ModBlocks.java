package com.sofodev.sworddisplay.registry;

import com.sofodev.sworddisplay.blocks.SDBlockItem;
import com.sofodev.sworddisplay.blocks.SwordCaseBlock;
import com.sofodev.sworddisplay.blocks.SwordDisplayBlock;
import com.sofodev.sworddisplay.blocks.SwordDisplayTile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
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
import static net.minecraft.world.level.block.Blocks.*;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    private static final Block[] BASE_BLOCKS = new Block[]{
            STONE, OAK_PLANKS, DARK_OAK_PLANKS, BIRCH_PLANKS, ACACIA_PLANKS, JUNGLE_PLANKS, SPRUCE_PLANKS,
            MANGROVE_PLANKS, CHERRY_PLANKS, CRIMSON_PLANKS,
            PRISMARINE, IRON_BLOCK, GOLD_BLOCK, DIAMOND_BLOCK, EMERALD_BLOCK, OBSIDIAN, STONE_BRICKS,
            END_STONE_BRICKS, NETHERITE_BLOCK, NETHER_BRICKS, PURPUR_BLOCK, QUARTZ_BLOCK, LAPIS_BLOCK, REDSTONE_BLOCK
    };

    // Map of special blocks → replacement recipe item
    public static final Map<String, Item> SPECIAL_BLOCKS = new HashMap<>();

    static {
        SPECIAL_BLOCKS.put("gold", Items.GOLD_INGOT);
        SPECIAL_BLOCKS.put("iron", Items.IRON_INGOT);
        SPECIAL_BLOCKS.put("diamond", Items.DIAMOND);
        SPECIAL_BLOCKS.put("emerald", Items.EMERALD);
        SPECIAL_BLOCKS.put("obsidian", Items.OBSIDIAN);
        SPECIAL_BLOCKS.put("stone_bricks", Items.STONE_BRICKS);
        SPECIAL_BLOCKS.put("end_stone_bricks", Items.END_STONE);
        SPECIAL_BLOCKS.put("netherite", Items.NETHERITE_INGOT);
        SPECIAL_BLOCKS.put("nether_bricks", Items.NETHER_BRICK);
        SPECIAL_BLOCKS.put("purpur", Items.PURPUR_BLOCK);
        SPECIAL_BLOCKS.put("quartz", Items.QUARTZ);
        SPECIAL_BLOCKS.put("lapis", Items.LAPIS_LAZULI);
        SPECIAL_BLOCKS.put("redstone", Items.REDSTONE);
        SPECIAL_BLOCKS.put("mangrove", Items.MANGROVE_PLANKS);
        SPECIAL_BLOCKS.put("cherry", Items.CHERRY_PLANKS);
        SPECIAL_BLOCKS.put("crimson", Items.CRIMSON_PLANKS);
    }


    // List of all registry entries
    public static final List<BlockRegistryEntry> REGISTRY_LIST = new ArrayList<>();
    public static final RegistryHelper registryHelper = new RegistryHelper(REGISTRY_LIST);

    static {
        registerAll();
    }

    public static void registerAll() {
        for (Block base : BASE_BLOCKS) {
            String baseName = getBaseName(base);

            RegistryObject<Block> display = registerBlockWithItem(baseName + "_sword_display",
                    () -> new SwordDisplayBlock(Block.Properties.copy(STONE)));

            RegistryObject<Block> swordCase = registerBlockWithItem(baseName + "_sword_case",
                    () -> new SwordCaseBlock(Block.Properties.copy(STONE)));

            // Determine top/side/core materials
            ItemLike top = base;
            ItemLike side = base;
            ItemLike core = base;
            boolean isSpecial = SPECIAL_BLOCKS.containsKey(baseName);
            if (isSpecial) {
                top = side = core = SPECIAL_BLOCKS.get(baseName);
            }

            REGISTRY_LIST.add(new BlockRegistryEntry(baseName, isSpecial, display, swordCase, top, side, core));
        }
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
                            .flatMap(entry -> Stream.of(entry.displayBlock.get(), entry.caseBlock.get()))
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

    public static class BlockRegistryEntry {
        private final String key;
        private final boolean isSpecial;
        private final RegistryObject<Block> displayBlock;
        private final RegistryObject<Block> caseBlock;
        private final ItemLike topMaterial;
        private final ItemLike sideMaterial;
        private final ItemLike coreMaterial;

        public BlockRegistryEntry(String key, boolean isSpecial, RegistryObject<Block> displayBlock,
                                  RegistryObject<Block> caseBlock, ItemLike top, ItemLike side, ItemLike core) {
            this.key = key;
            this.isSpecial = isSpecial;
            this.displayBlock = displayBlock;
            this.caseBlock = caseBlock;
            this.topMaterial = top;
            this.sideMaterial = side;
            this.coreMaterial = core;
        }

        public String getKey() {
            return key;
        }

        public boolean isSpecial() {
            return isSpecial;
        }

        public RegistryObject<Block> getDisplayBlock() {
            return displayBlock;
        }

        public RegistryObject<Block> getCaseBlock() {
            return caseBlock;
        }

        public ItemLike getTopMaterial() {
            return topMaterial;
        }

        public ItemLike getSideMaterial() {
            return sideMaterial;
        }

        public ItemLike getCoreMaterial() {
            return coreMaterial;
        }
    }
}
