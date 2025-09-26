package com.sofodev.sworddisplay.registry;

import com.sofodev.sworddisplay.blocks.SDBlockItem;
import com.sofodev.sworddisplay.blocks.SwordCaseBlock;
import com.sofodev.sworddisplay.blocks.SwordDisplayBlock;
import com.sofodev.sworddisplay.blocks.SwordDisplayTile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static com.sofodev.sworddisplay.registry.ModItems.ITEMS;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    //region Normal Sword Displays
    public static final RegistryObject<Block> SWORD_DISPLAY = registerBlockWithItem("sword_display", () -> new SwordDisplayBlock(copy(STONE)));
    public static final RegistryObject<Block> WOODEN_SWORD_DISPLAY = registerBlockWithItem("wooden_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> DARK_OAK_SWORD_DISPLAY = registerBlockWithItem("dark_oak_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> BIRCH_SWORD_DISPLAY = registerBlockWithItem("birch_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> ACACIA_SWORD_DISPLAY = registerBlockWithItem("acacia_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> JUNGLE_SWORD_DISPLAY = registerBlockWithItem("jungle_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> SPRUCE_SWORD_DISPLAY = registerBlockWithItem("spruce_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> PRISMARINE_SWORD_DISPLAY = registerBlockWithItem("prismarine_sword_display", () -> new SwordDisplayBlock(copy(STONE)));
    public static final RegistryObject<Block> IRON_SWORD_DISPLAY = registerBlockWithItem("iron_sword_display", () -> new SwordDisplayBlock(copy(STONE)));
    public static final RegistryObject<Block> GOLDEN_SWORD_DISPLAY = registerBlockWithItem("golden_sword_display", () -> new SwordDisplayBlock(copy(STONE)));
    public static final RegistryObject<Block> DIAMOND_SWORD_DISPLAY = registerBlockWithItem("diamond_sword_display", () -> new SwordDisplayBlock(copy(DIAMOND_BLOCK)));
    public static final RegistryObject<Block> EMERALD_SWORD_DISPLAY = registerBlockWithItem("emerald_sword_display", () -> new SwordDisplayBlock(copy(DIAMOND_BLOCK)));
    //endregion
    //region Sword Displays with Glass
    public static final RegistryObject<Block> SWORD_CASE = registerBlockWithItem("sword_case", () -> new SwordCaseBlock(copy(STONE)));
    public static final RegistryObject<Block> WOODEN_SWORD_CASE = registerBlockWithItem("wooden_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> DARK_OAK_SWORD_CASE = registerBlockWithItem("dark_oak_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> BIRCH_SWORD_CASE = registerBlockWithItem("birch_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> ACACIA_SWORD_CASE = registerBlockWithItem("acacia_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> JUNGLE_SWORD_CASE = registerBlockWithItem("jungle_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> SPRUCE_SWORD_CASE = registerBlockWithItem("spruce_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
    public static final RegistryObject<Block> PRISMARINE_SWORD_CASE = registerBlockWithItem("prismarine_sword_case", () -> new SwordCaseBlock(copy(STONE)));
    public static final RegistryObject<Block> IRON_SWORD_CASE = registerBlockWithItem("iron_sword_case", () -> new SwordCaseBlock(copy(STONE)));
    public static final RegistryObject<Block> GOLDEN_SWORD_CASE = registerBlockWithItem("golden_sword_case", () -> new SwordCaseBlock(copy(STONE)));
    public static final RegistryObject<Block> DIAMOND_SWORD_CASE = registerBlockWithItem("diamond_sword_case", () -> new SwordCaseBlock(copy(DIAMOND_BLOCK)));
    public static final RegistryObject<Block> EMERALD_SWORD_CASE = registerBlockWithItem("emerald_sword_case", () -> new SwordCaseBlock(copy(DIAMOND_BLOCK)));

    //endregion

    public static <BLOCK extends Block> RegistryObject<BLOCK> registerBlockWithItem(String name, DeferredRegister<Block> blocks, DeferredRegister<Item> items, Supplier<BLOCK> blockSupplier, Function<BLOCK, Item> itemFactory) {
        RegistryObject<BLOCK> block = blocks.register(name, blockSupplier);
        items.register(name, () -> itemFactory.apply(block.get()));
        return block;
    }

    public static <BLOCK extends Block> RegistryObject<BLOCK> registerBlockWithItem(String name, Supplier<BLOCK> blockSupplier, Function<BLOCK, Item> itemFactory) {
        return registerBlockWithItem(name, BLOCKS, ITEMS, blockSupplier, itemFactory);
    }

    public static <BLOCK extends Block> RegistryObject<BLOCK> registerBlockWithItem(String name, Supplier<BLOCK> blockSupplier) {
        return registerBlockWithItem(name, BLOCKS, ITEMS, blockSupplier, SDBlockItem::new);
    }

    public static final RegistryObject<BlockEntityType<SwordDisplayTile>> SWORD_DISPLAY_TYPE = TILE_ENTITIES.register("sword_display",
            () -> build(BlockEntityType.Builder.of(SwordDisplayTile::new,
                    SWORD_DISPLAY.get(),
                    WOODEN_SWORD_DISPLAY.get(),
                    DARK_OAK_SWORD_DISPLAY.get(),
                    BIRCH_SWORD_DISPLAY.get(),
                    ACACIA_SWORD_DISPLAY.get(),
                    JUNGLE_SWORD_DISPLAY.get(),
                    SPRUCE_SWORD_DISPLAY.get(),
                    PRISMARINE_SWORD_DISPLAY.get(),
                    IRON_SWORD_DISPLAY.get(),
                    GOLDEN_SWORD_DISPLAY.get(),
                    DIAMOND_SWORD_DISPLAY.get(),
                    EMERALD_SWORD_DISPLAY.get(),
                    SWORD_CASE.get(),
                    WOODEN_SWORD_CASE.get(),
                    DARK_OAK_SWORD_CASE.get(),
                    BIRCH_SWORD_CASE.get(),
                    ACACIA_SWORD_CASE.get(),
                    JUNGLE_SWORD_CASE.get(),
                    SPRUCE_SWORD_CASE.get(),
                    PRISMARINE_SWORD_CASE.get(),
                    IRON_SWORD_CASE.get(),
                    GOLDEN_SWORD_CASE.get(),
                    DIAMOND_SWORD_CASE.get(),
                    EMERALD_SWORD_CASE.get())));

    private static <T extends BlockEntity> BlockEntityType<T> build(BlockEntityType.Builder<T> builder) {
        return builder.build(null);
    }

}
