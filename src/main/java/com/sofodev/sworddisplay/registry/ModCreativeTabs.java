package com.sofodev.sworddisplay.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static com.sofodev.sworddisplay.registry.ModBlocks.registryHelper;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> SD_GROUP = register("core", () -> CreativeModeTab.builder()
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


    public static RegistryObject<CreativeModeTab> register(String name, Supplier<? extends CreativeModeTab> sup) {
        return CREATIVE_MODE_TABS.register(name, sup);
    }
}
