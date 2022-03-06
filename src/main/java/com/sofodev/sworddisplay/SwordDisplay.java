package com.sofodev.sworddisplay;

import com.sofodev.sworddisplay.blocks.SwordCaseBlock;
import com.sofodev.sworddisplay.blocks.SwordDisplayBlock;
import com.sofodev.sworddisplay.blocks.SwordDisplayTile;
import com.sofodev.sworddisplay.blocks.TESRSwordDisplay;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.sofodev.sworddisplay.SwordDisplay.RegistryEvents.*;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod("sworddisplay")
public class SwordDisplay {
    public static final String MODID = "sworddisplay";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);

    public static final CreativeModeTab SD_GROUP = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SWORD_DISPLAY.get());
        }
    };

    public SwordDisplay() {
        MinecraftForge.EVENT_BUS.register(this);
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        this.setRenderLayer(SWORD_CASE, WOODEN_SWORD_CASE, DARK_OAK_SWORD_CASE, BIRCH_SWORD_CASE,
                ACACIA_SWORD_CASE, JUNGLE_SWORD_CASE, SPRUCE_SWORD_CASE, PRISMARINE_SWORD_CASE,
                IRON_SWORD_CASE, GOLDEN_SWORD_CASE, DIAMOND_SWORD_CASE, EMERALD_SWORD_CASE
        );
        BlockEntityRenderers.register(SWORD_DISPLAY_TYPE.get(), TESRSwordDisplay::new);
    }

    @SafeVarargs
    private final void setRenderLayer(RegistryObject<Block>... blocks) {
        Arrays.stream(blocks).forEach(block -> ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout()));
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = MOD)
    public static class RegistryEvents {

        public static List<RegistryObject<Block>> blocks = new ArrayList<>();

        //Normal Sword Displays
        public static final RegistryObject<Block> SWORD_DISPLAY = regWithItem("sword_display", () -> new SwordDisplayBlock(copy(STONE)));
        public static final RegistryObject<Block> WOODEN_SWORD_DISPLAY = regWithItem("wooden_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> DARK_OAK_SWORD_DISPLAY = regWithItem("dark_oak_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> BIRCH_SWORD_DISPLAY = regWithItem("birch_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> ACACIA_SWORD_DISPLAY = regWithItem("acacia_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> JUNGLE_SWORD_DISPLAY = regWithItem("jungle_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> SPRUCE_SWORD_DISPLAY = regWithItem("spruce_sword_display", () -> new SwordDisplayBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> PRISMARINE_SWORD_DISPLAY = regWithItem("prismarine_sword_display", () -> new SwordDisplayBlock(copy(STONE)));
        public static final RegistryObject<Block> IRON_SWORD_DISPLAY = regWithItem("iron_sword_display", () -> new SwordDisplayBlock(copy(STONE)));
        public static final RegistryObject<Block> GOLDEN_SWORD_DISPLAY = regWithItem("golden_sword_display", () -> new SwordDisplayBlock(copy(STONE)));
        public static final RegistryObject<Block> DIAMOND_SWORD_DISPLAY = regWithItem("diamond_sword_display", () -> new SwordDisplayBlock(copy(DIAMOND_BLOCK)));
        public static final RegistryObject<Block> EMERALD_SWORD_DISPLAY = regWithItem("emerald_sword_display", () -> new SwordDisplayBlock(copy(DIAMOND_BLOCK)));

        //Sword Displays with Glass
        public static final RegistryObject<Block> SWORD_CASE = regWithItem("sword_case", () -> new SwordCaseBlock(copy(STONE)));
        public static final RegistryObject<Block> WOODEN_SWORD_CASE = regWithItem("wooden_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> DARK_OAK_SWORD_CASE = regWithItem("dark_oak_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> BIRCH_SWORD_CASE = regWithItem("birch_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> ACACIA_SWORD_CASE = regWithItem("acacia_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> JUNGLE_SWORD_CASE = regWithItem("jungle_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> SPRUCE_SWORD_CASE = regWithItem("spruce_sword_case", () -> new SwordCaseBlock(copy(OAK_PLANKS)));
        public static final RegistryObject<Block> PRISMARINE_SWORD_CASE = regWithItem("prismarine_sword_case", () -> new SwordCaseBlock(copy(STONE)));
        public static final RegistryObject<Block> IRON_SWORD_CASE = regWithItem("iron_sword_case", () -> new SwordCaseBlock(copy(STONE)));
        public static final RegistryObject<Block> GOLDEN_SWORD_CASE = regWithItem("golden_sword_case", () -> new SwordCaseBlock(copy(STONE)));
        public static final RegistryObject<Block> DIAMOND_SWORD_CASE = regWithItem("diamond_sword_case", () -> new SwordCaseBlock(copy(DIAMOND_BLOCK)));
        public static final RegistryObject<Block> EMERALD_SWORD_CASE = regWithItem("emerald_sword_case", () -> new SwordCaseBlock(copy(DIAMOND_BLOCK)));

        public static final Set<RegistryObject<BlockItem>> ITEM_BLOCKS = registerBlockItems();

        public static RegistryObject<Block> register(String name, Supplier<? extends Block> sup) {
            return BLOCKS.register(name, sup);
        }        //TE
        public static final RegistryObject<BlockEntityType<SwordDisplayTile>> SWORD_DISPLAY_TYPE = TILE_ENTITIES.register("sword_display",
                () -> build(BlockEntityType.Builder.of(SwordDisplayTile::new,
                        SWORD_DISPLAY.get(), WOODEN_SWORD_DISPLAY.get(), DARK_OAK_SWORD_DISPLAY.get(), BIRCH_SWORD_DISPLAY.get(),
                        ACACIA_SWORD_DISPLAY.get(), JUNGLE_SWORD_DISPLAY.get(), SPRUCE_SWORD_DISPLAY.get(), PRISMARINE_SWORD_DISPLAY.get(),
                        IRON_SWORD_DISPLAY.get(), GOLDEN_SWORD_DISPLAY.get(), DIAMOND_SWORD_DISPLAY.get(), EMERALD_SWORD_DISPLAY.get(),
                        SWORD_CASE.get(), WOODEN_SWORD_CASE.get(), DARK_OAK_SWORD_CASE.get(), BIRCH_SWORD_CASE.get(),
                        ACACIA_SWORD_CASE.get(), JUNGLE_SWORD_CASE.get(), SPRUCE_SWORD_CASE.get(), PRISMARINE_SWORD_CASE.get(),
                        IRON_SWORD_CASE.get(), GOLDEN_SWORD_CASE.get(), DIAMOND_SWORD_CASE.get(), EMERALD_SWORD_CASE.get()
                )));

        public static RegistryObject<Block> regWithItem(String name, Supplier<? extends Block> sup) {
            RegistryObject<Block> block = register(name, sup);
            blocks.add(block);
            return block;
        }

        private static <T extends BlockEntity> BlockEntityType<T> build(BlockEntityType.Builder<T> builder) {
            return builder.build(null);
        }

        public static Set<RegistryObject<BlockItem>> registerBlockItems() {
            return blocks.stream()
                    .map(block -> ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(SD_GROUP))))
                    .collect(Collectors.toSet());
        }


    }
}
