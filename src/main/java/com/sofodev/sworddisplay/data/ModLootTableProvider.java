package com.sofodev.sworddisplay.data;

import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.sofodev.sworddisplay.registry.ModBlocks.registryHelper;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output,
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)),
                provider
        );
    }

    private static class ModBlockLootTables extends BlockLootSubProvider {
        protected ModBlockLootTables(HolderLookup.Provider provider) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        protected void generate() {
            List<ModBlocks.BlockRegistryEntry> entries = registryHelper.getRegistryList(); // assuming getter exists

            for (ModBlocks.BlockRegistryEntry entry : entries) {
                DeferredHolder<Block, Block> caseBlock = entry.blocks().caseBlock();
                DeferredHolder<Block, Block> displayBlock = entry.blocks().displayBlock();
                DeferredHolder<Block, Block> wallBlock = entry.blocks().wallDisplay();
                dropSelf(caseBlock.get());
                dropSelf(displayBlock.get());
                dropSelf(wallBlock.get());
            }
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            List<ModBlocks.BlockRegistryEntry> entries = registryHelper.getRegistryList();
            List<Block> blocks = new ArrayList<>();

            for (ModBlocks.BlockRegistryEntry entry : entries) {
                blocks.add(entry.blocks().caseBlock().value());
                blocks.add(entry.blocks().displayBlock().value());
                blocks.add(entry.blocks().wallDisplay().value());
            }

            return blocks;
        }
    }
}