package com.sofodev.sworddisplay.data.server;

import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public final class SDBlockLoot extends BlockLootSubProvider {
    public SDBlockLoot(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
    }

    @Override
    protected void generate() {
        for (ModBlocks.BlockRegistryEntry entry : ModBlocks.REGISTRY_LIST) {
            dropSelf(entry.blocks().displayBlock().get());
            dropSelf(entry.blocks().caseBlock().get());
            dropSelf(entry.blocks().wallDisplay().get());
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.REGISTRY_LIST.stream()
                .flatMap(entry -> java.util.stream.Stream.of(
                        entry.blocks().displayBlock().get(),
                        entry.blocks().caseBlock().get(),
                        entry.blocks().wallDisplay().get()))
                .toList();
    }
}
