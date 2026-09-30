package com.sofodev.sworddisplay.data.server;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;

public final class SDServerData {
    private SDServerData() {
    }

    public static void gatherData(GatherDataEvent.Server event) {
        event.createProvider(SDRecipeProvider.Runner::new);
        event.createProvider(SDBlockTagsProvider::new);
        event.createProvider((output, lookupProvider) -> new LootTableProvider(
                output,
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(SDBlockLoot::new, LootContextParamSets.BLOCK)),
                lookupProvider
        ));
    }
}
