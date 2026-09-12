package com.sofodev.sworddisplay.registry;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);

    public static <ITEM extends Item> DeferredHolder<Item, ITEM> register(String name, DeferredRegister<Item> items, Supplier<ITEM> itemSupplier) {
        return items.register(name, itemSupplier);
    }

    public static <ITEM extends Item> DeferredHolder<Item, ITEM> register(String name, Supplier<ITEM> itemSupplier) {
        return register(name, ITEMS, itemSupplier);
    }
}
