package com.sofodev.sworddisplay.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static com.sofodev.sworddisplay.registry.ModBlocks.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    public static final RegistryObject<CreativeModeTab> SD_GROUP = register("core", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(SWORD_DISPLAY.get()))
            .title(Component.translatable("tabs.sworddisplay.core"))
            .withBackgroundLocation(ResourceLocation.fromNamespaceAndPath(MODID, "textures/gui/container/creative_inventory/tab_sword_displays.png"))
            .withLabelColor(0xFFFFFF)
            .withSearchBar(40)
            .displayItems((featureFlags, output) -> {
                output.accept(SWORD_DISPLAY.get());
                output.accept(SWORD_CASE.get());
                output.accept(WOODEN_SWORD_DISPLAY.get());
                output.accept(WOODEN_SWORD_CASE.get());
                output.accept(DARK_OAK_SWORD_DISPLAY.get());
                output.accept(DARK_OAK_SWORD_CASE.get());
                output.accept(BIRCH_SWORD_DISPLAY.get());
                output.accept(BIRCH_SWORD_CASE.get());
                output.accept(ACACIA_SWORD_DISPLAY.get());
                output.accept(ACACIA_SWORD_CASE.get());
                output.accept(JUNGLE_SWORD_DISPLAY.get());
                output.accept(JUNGLE_SWORD_CASE.get());
                output.accept(SPRUCE_SWORD_DISPLAY.get());
                output.accept(SPRUCE_SWORD_CASE.get());
                output.accept(PRISMARINE_SWORD_DISPLAY.get());
                output.accept(PRISMARINE_SWORD_CASE.get());
                output.accept(IRON_SWORD_DISPLAY.get());
                output.accept(IRON_SWORD_CASE.get());
                output.accept(GOLDEN_SWORD_DISPLAY.get());
                output.accept(GOLDEN_SWORD_CASE.get());
                output.accept(DIAMOND_SWORD_DISPLAY.get());
                output.accept(DIAMOND_SWORD_CASE.get());
                output.accept(EMERALD_SWORD_DISPLAY.get());
                output.accept(EMERALD_SWORD_CASE.get());
            }).build());

    public static RegistryObject<CreativeModeTab> register(String name, Supplier<? extends CreativeModeTab> sup) {
        return CREATIVE_MODE_TABS.register(name, sup);
    }
}
