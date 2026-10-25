package com.sofodev.sworddisplay.registry;

import net.minecraft.core.registries.Registries;
import com.sofodev.sworddisplay.config.SDConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static com.sofodev.sworddisplay.registry.ModBlocks.registryHelper;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SD_GROUP = register("core", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(registryHelper.getDisplay("stone").get()))
            .title(Component.translatable("tabs.sworddisplay.core"))
            .displayItems((flags, output) -> registryHelper.forEach(entry -> {
                if (SDConfig.isEnabled(entry.blocks().displayBlock().get())) output.accept(entry.blocks().displayBlock().get().asItem());
                if (SDConfig.isEnabled(entry.blocks().caseBlock().get())) output.accept(entry.blocks().caseBlock().get().asItem());
                if (!entry.key().equals("slime_block")) if (SDConfig.isEnabled(entry.blocks().wallDisplay().get())) output.accept(entry.blocks().wallDisplay().get().asItem());
            })).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PLAQUES = register("plaques", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.STYLED_ENTRIES.stream().filter(e -> e.style().equals("plaque") && e.key().equals("oak")).findFirst().orElseThrow().blocks().wallDisplay().get()))
            .title(Component.translatable("tabs.sworddisplay.plaques"))
            .displayItems((flags, output) -> ModBlocks.STYLED_ENTRIES.stream().filter(e -> e.style().equals("plaque")).forEach(entry -> {
                if (SDConfig.isEnabled(entry.blocks().displayBlock().get())) output.accept(entry.blocks().displayBlock().get().asItem());
                if (SDConfig.isEnabled(entry.blocks().caseBlock().get())) output.accept(entry.blocks().caseBlock().get().asItem());
                if (!entry.key().equals("slime_block")) if (SDConfig.isEnabled(entry.blocks().wallDisplay().get())) output.accept(entry.blocks().wallDisplay().get().asItem());
            })).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RACKS = register("racks", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.STYLED_ENTRIES.stream().filter(e -> e.style().equals("rack") && e.key().equals("oak")).findFirst().orElseThrow().blocks().displayBlock().get()))
            .title(Component.translatable("tabs.sworddisplay.racks"))
            .displayItems((flags, output) -> ModBlocks.STYLED_ENTRIES.stream().filter(e -> e.style().equals("rack")).forEach(entry -> {
                if (SDConfig.isEnabled(entry.blocks().displayBlock().get())) output.accept(entry.blocks().displayBlock().get().asItem());
                if (SDConfig.isEnabled(entry.blocks().caseBlock().get())) output.accept(entry.blocks().caseBlock().get().asItem());
                if (!entry.key().equals("slime_block")) if (SDConfig.isEnabled(entry.blocks().wallDisplay().get())) output.accept(entry.blocks().wallDisplay().get().asItem());
            })).build());

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String name, Supplier<? extends CreativeModeTab> sup) {
        return CREATIVE_MODE_TABS.register(name, sup);
    }
}
