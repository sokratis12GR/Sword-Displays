package com.sofodev.sworddisplay.data;

import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.sofodev.sworddisplay.registry.ModBlocks.registryHelper;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput output) {
        super(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }

    private static class ModBlockLootTables extends BlockLootSubProvider {
        protected ModBlockLootTables() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            List<ModBlocks.BlockRegistryEntry> entries = registryHelper.registryList(); // assuming getter exists

            for (ModBlocks.BlockRegistryEntry entry : entries) {
                RegistryObject<Block> caseBlock = entry.blocks().caseBlock();
                RegistryObject<Block> displayBlock = entry.blocks().displayBlock();
                RegistryObject<Block> wallBlock = entry.blocks().wallDisplay();
                dropSelf(caseBlock.get());
                dropSelf(displayBlock.get());
                dropSelf(wallBlock.get());
            }
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            List<ModBlocks.BlockRegistryEntry> entries = registryHelper.registryList();
            List<Block> blocks = new ArrayList<>();
            for (ModBlocks.BlockRegistryEntry entry : entries) {
                entry.blocks().caseBlock().ifPresent(blocks::add);
                entry.blocks().displayBlock().ifPresent(blocks::add);
                entry.blocks().wallDisplay().ifPresent(blocks::add);
            }
            return blocks;
        }
    }
}