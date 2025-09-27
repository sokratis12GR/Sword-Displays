package com.sofodev.sworddisplay.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Consumer;

public class RegistryHelper {

    private final List<ModBlocks.BlockRegistryEntry> registryList;

    public RegistryHelper(List<ModBlocks.BlockRegistryEntry> registryList) {
        this.registryList = registryList;
    }

    // Get the display block by base name
    public RegistryObject<Block> getDisplay(String baseName) {
        return registryList.stream()
                .filter(entry -> entry.getKey().equals(baseName))
                .map(ModBlocks.BlockRegistryEntry::getDisplayBlock)
                .findFirst()
                .orElse(null);
    }

    // Get the case block by base name
    public RegistryObject<Block> getCase(String baseName) {
        return registryList.stream()
                .filter(entry -> entry.getKey().equals(baseName))
                .map(ModBlocks.BlockRegistryEntry::getCaseBlock)
                .findFirst()
                .orElse(null);
    }

    // Get item from display block
    public Item getDisplayItem(String baseName) {
        RegistryObject<Block> block = getDisplay(baseName);
        return block != null ? block.get().asItem() : null;
    }

    // Get item from case block
    public Item getCaseItem(String baseName) {
        RegistryObject<Block> block = getCase(baseName);
        return block != null ? block.get().asItem() : null;
    }

    // Iterate all entries
    public void forEach(Consumer<ModBlocks.BlockRegistryEntry> consumer) {
        registryList.forEach(consumer);
    }

    public List<ModBlocks.BlockRegistryEntry> getRegistryList() {
        return registryList;
    }
}
