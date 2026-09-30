package com.sofodev.sworddisplay.data.server;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;

public final class SDServerData {
    private SDServerData() {}

    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookup = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new SDRecipeProvider.Runner(output, lookup));
        generator.addProvider(event.includeServer(), new SDBlockTagsProvider(
                output,
                lookup,
                event.getExistingFileHelper()
        ));
        generator.addProvider(event.includeServer(), new LootTableProvider(
                output,
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(SDBlockLoot::new, LootContextParamSets.BLOCK)),
                lookup
        ));
    }
}
