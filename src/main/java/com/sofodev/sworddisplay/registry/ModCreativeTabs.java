package com.sofodev.sworddisplay.registry;

import net.minecraft.core.registries.Registries;
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
            .displayItems((flags, output) -> {
                registryHelper.forEach(entry -> {
                    output.accept(entry.blocks().displayBlock().get().asItem());
                    output.accept(entry.blocks().caseBlock().get().asItem());
                    output.accept(entry.blocks().wallDisplay().get().asItem());
                });
            })
            .build());


    public static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String name, Supplier<? extends CreativeModeTab> sup) {
        return CREATIVE_MODE_TABS.register(name, sup);
    }
}
