package com.sofodev.sworddisplay.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.function.Consumer;

public class RegistryHelper {

    private final List<ModBlocks.BlockRegistryEntry> registryList;

    public RegistryHelper(List<ModBlocks.BlockRegistryEntry> registryList) {
        this.registryList = registryList;
    }

    // Get the display block by base name
    public DeferredHolder<Block, Block> getDisplay(String baseName) {
        return registryList.stream()
                .filter(entry -> entry.key().equals(baseName))
                .map(entry -> entry.blocks().displayBlock())
                .findFirst()
                .orElse(null);
    }

    // Get the case block by base name
    public DeferredHolder<Block, Block> getCase(String baseName) {
        return registryList.stream()
                .filter(entry -> entry.key().equals(baseName))
                .map(entry -> entry.blocks().caseBlock())
                .findFirst()
                .orElse(null);
    }

    // Get item from display block
    public Item getDisplayItem(String baseName) {
        DeferredHolder<Block, Block> block = getDisplay(baseName);
        return block != null ? block.get().asItem() : null;
    }

    // Get item from case block
    public Item getCaseItem(String baseName) {
        DeferredHolder<Block, Block> block = getCase(baseName);
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
