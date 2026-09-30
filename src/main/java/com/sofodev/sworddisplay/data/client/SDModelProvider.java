package com.sofodev.sworddisplay.data.client;

import com.google.gson.JsonObject;
import com.sofodev.sworddisplay.SwordDisplay;
import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public final class SDModelProvider implements DataProvider {
    private final PackOutput.PathProvider blockStates;
    private final PackOutput.PathProvider models;
    private final PackOutput.PathProvider items;

    public SDModelProvider(PackOutput output) {
        this.blockStates = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.models = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
        this.items = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (ModBlocks.BlockRegistryEntry entry : ModBlocks.REGISTRY_LIST) {
            String texture = materialTexture(entry.key());
            generate(output, futures, entry.blocks().displayBlock().get(), "sword_display_base", texture, true);
            generate(output, futures, entry.blocks().caseBlock().get(), "sword_case_base", texture, true);
            generate(output, futures, entry.blocks().wallDisplay().get(), "wall_display_base", texture, false);
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private void generate(CachedOutput output, List<CompletableFuture<?>> futures, Block block, String parentName, String texture, boolean texture1) {
        Identifier id = BuiltInRegistries.BLOCK.getKey(block);
        Identifier modelId = Identifier.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath());

        JsonObject model = new JsonObject();
        model.addProperty("parent", SwordDisplay.MODID + ":block/base/" + parentName);
        JsonObject textures = new JsonObject();
        textures.addProperty("0", texture);
        if (texture1) {
            textures.addProperty("1", texture);
        }
        textures.addProperty("particle", texture);
        model.add("textures", textures);
        futures.add(DataProvider.saveStable(output, model, models.json(modelId)));

        JsonObject state = new JsonObject();
        JsonObject variants = new JsonObject();
        addFacing(variants, "south", modelId, 0);
        addFacing(variants, "west", modelId, 90);
        addFacing(variants, "north", modelId, 180);
        addFacing(variants, "east", modelId, 270);
        state.add("variants", variants);
        futures.add(DataProvider.saveStable(output, state, blockStates.json(id)));

        Identifier itemModelId = Identifier.fromNamespaceAndPath(id.getNamespace(), "item/" + id.getPath());
        JsonObject itemModel = new JsonObject();
        itemModel.addProperty("parent", modelId.toString());
        futures.add(DataProvider.saveStable(output, itemModel, models.json(itemModelId)));

        JsonObject clientItem = new JsonObject();
        JsonObject clientModel = new JsonObject();
        clientModel.addProperty("type", "minecraft:model");
        clientModel.addProperty("model", itemModelId.toString());
        clientItem.add("model", clientModel);
        futures.add(DataProvider.saveStable(output, clientItem, items.json(id)));
    }

    private static void addFacing(JsonObject variants, String facing, Identifier model, int y) {
        JsonObject variant = new JsonObject();
        variant.addProperty("model", model.toString());
        if (y != 0) {
            variant.addProperty("y", y);
        }
        variants.add("facing=" + facing, variant);
    }

    private static String materialTexture(String key) {
        if (key.equals("quartz")) {
            return "minecraft:block/quartz_block_side";
        }
        Block materialBlock = ModBlocks.BLOCK_AND_ITEM_MAP.get(key).block();
        Identifier blockId = BuiltInRegistries.BLOCK.getKey(materialBlock);
        return blockId.getNamespace() + ":block/" + blockId.getPath();
    }

    @Override
    public String getName() {
        return "Sword Displays block and item models";
    }
}
