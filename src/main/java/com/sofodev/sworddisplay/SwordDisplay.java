package com.sofodev.sworddisplay;

import com.sofodev.sworddisplay.blocks.SwordCaseBlock;
import com.sofodev.sworddisplay.blocks.SwordDisplayBlock;
import com.sofodev.sworddisplay.blocks.SwordDisplayTile;
import com.sofodev.sworddisplay.blocks.TESRSwordDisplay;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.sofodev.sworddisplay.SwordDisplay.RegistryEvents.*;
import static java.lang.String.format;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.*;

@Mod("sworddisplay")
public class SwordDisplay {
    public static final String MODID = "sworddisplay";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);

    public static final ItemGroup SD_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), MODID) {
        @Override
        public ItemStack createIcon() {
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
        RenderTypeLookup.setRenderLayer(SWORD_CASE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(WOODEN_SWORD_CASE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(PRISMARINE_SWORD_CASE.get(), RenderType.getTranslucent());
        ClientRegistry.bindTileEntityRenderer(SWORD_DISPLAY_TYPE.get(), TESRSwordDisplay::new);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = MOD)
    public static class RegistryEvents {

        public static List<RegistryObject<Block>> blocks = new ArrayList<>();

        //Normal Sword Displays
        public static final RegistryObject<Block> SWORD_DISPLAY = regWithItem("sword_display", SwordDisplayBlock::new);
        public static final RegistryObject<Block> WOODEN_SWORD_DISPLAY = regWithItem("wooden_sword_display", SwordDisplayBlock::new);
        public static final RegistryObject<Block> PRISMARINE_SWORD_DISPLAY = regWithItem("prismarine_sword_display", SwordDisplayBlock::new);
        //Sword Displays with Glass
        public static final RegistryObject<Block> SWORD_CASE = regWithItem("sword_case", SwordCaseBlock::new);
        public static final RegistryObject<Block> WOODEN_SWORD_CASE = regWithItem("wooden_sword_case", SwordCaseBlock::new);
        public static final RegistryObject<Block> PRISMARINE_SWORD_CASE = regWithItem("prismarine_sword_case", SwordCaseBlock::new);
        //TE
        public static final RegistryObject<TileEntityType<SwordDisplayTile>> SWORD_DISPLAY_TYPE = TILE_ENTITIES.register("sword_display",
                () -> build(TileEntityType.Builder.create(SwordDisplayTile::new,
                        SWORD_DISPLAY.get(), WOODEN_SWORD_DISPLAY.get(), PRISMARINE_SWORD_DISPLAY.get(),
                        SWORD_CASE.get(), WOODEN_SWORD_CASE.get(), PRISMARINE_SWORD_CASE.get()
                )));
        public static final Set<RegistryObject<BlockItem>> ITEM_BLOCKS = registerBlockItems();

        public static RegistryObject<Block> register(String name, Supplier<? extends Block> sup) {
            return BLOCKS.register(name, sup);
        }

        public static RegistryObject<Block> regWithItem(String name, Supplier<? extends Block> sup) {
            RegistryObject<Block> block = register(name, sup);
            blocks.add(block);
            return block;
        }

        private static <T extends TileEntity> TileEntityType<T> build(TileEntityType.Builder<T> builder) {
            return builder.build(null);
        }

        public static Set<RegistryObject<BlockItem>> registerBlockItems() {
            return blocks.stream()
                    .map(block -> ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().group(SD_GROUP))))
                    .collect(Collectors.toSet());
        }
    }
}
