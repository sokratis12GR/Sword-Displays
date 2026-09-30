package com.sofodev.sworddisplay.data.server;

import com.sofodev.sworddisplay.SwordDisplay;
import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public final class SDBlockTagsProvider extends BlockTagsProvider {
    public static final TagKey<Block> DISPLAYS = TagKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(SwordDisplay.MODID, "displays")
    );

    public SDBlockTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, SwordDisplay.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        var displays = tag(DISPLAYS);
        var axe = tag(BlockTags.MINEABLE_WITH_AXE);
        var pickaxe = tag(BlockTags.MINEABLE_WITH_PICKAXE);

        for (ModBlocks.BlockRegistryEntry entry : ModBlocks.REGISTRY_LIST) {
            Block display = entry.blocks().displayBlock().get();
            Block swordCase = entry.blocks().caseBlock().get();
            Block wallDisplay = entry.blocks().wallDisplay().get();

            displays.add(display, swordCase, wallDisplay);

            if (entry.isWooden()) {
                axe.add(display, swordCase, wallDisplay);
            } else {
                pickaxe.add(display, swordCase, wallDisplay);
            }
        }

        tag(BlockTags.NEEDS_STONE_TOOL).addTag(DISPLAYS);
    }
}