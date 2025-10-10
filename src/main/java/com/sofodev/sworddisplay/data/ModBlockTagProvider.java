package com.sofodev.sworddisplay.data;

import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;
import static com.sofodev.sworddisplay.registry.ModBlocks.registryHelper;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        List<ModBlocks.BlockRegistryEntry> entries = registryHelper.registryList(); // assuming getter exists
        TagKey<Block> SWORD_DISPLAYS = modTag("displays");

        for (ModBlocks.BlockRegistryEntry entry : entries) {
            RegistryObject<Block> caseRO = entry.blocks().caseBlock();
            RegistryObject<Block> displayRO = entry.blocks().displayBlock();
            RegistryObject<Block> wallDisplayRO = entry.blocks().wallDisplay();
            addAllItems(SWORD_DISPLAYS, caseRO, displayRO, wallDisplayRO);
            if (entry.isWooden()) {
                tag(BlockTags.MINEABLE_WITH_AXE).add(caseRO.get(), displayRO.get(), wallDisplayRO.get());
            } else {
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(caseRO.get(), displayRO.get(), wallDisplayRO.get());
            }
        }
        mirrorMinecraftTag(BlockTags.NEEDS_STONE_TOOL, SWORD_DISPLAYS);

    }

    private TagKey<Block> modTag(String path) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MODID, path));
    }


    private TagKey<Block> mirrorMinecraftTag(TagKey<Block> tagKey, TagKey<Block>... mirrors) {

        if (mirrors != null) {
            for (TagKey<Block> mirror : mirrors) {
                tag(tagKey).addTag(mirror);
            }
        }

        return tagKey;
    }

    private TagKey<Block> mirrorMinecraftTag(TagKey<Block> tagKey, String... mirrors) {

        if (mirrors != null) {
            for (String mirror : mirrors) {
                ResourceLocation loc = mirror.contains(":")
                        ? ResourceLocation.parse(mirror)
                        : ResourceLocation.withDefaultNamespace(mirror);

                tag(tagKey).addTag(TagKey.create(Registries.BLOCK, loc));
            }
        }

        return tagKey;
    }

    @SafeVarargs
    private void addAllItems(TagKey<Block> tagKey, RegistryObject<? extends Block>... items) {
        for (RegistryObject<? extends Block> obj : items) {
            tag(tagKey).add(ResourceKey.create(Registries.BLOCK, obj.getId()));
        }
    }
}