package com.sofodev.sworddisplay.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static <ITEM extends Item> RegistryObject<ITEM> register(String name, DeferredRegister<Item> items, Supplier<ITEM> itemSupplier) {
        return items.register(name, itemSupplier);
    }

    public static <ITEM extends Item> RegistryObject<ITEM> register(String name, Supplier<ITEM> itemSupplier) {
        return register(name, ITEMS, itemSupplier);
    }
}
