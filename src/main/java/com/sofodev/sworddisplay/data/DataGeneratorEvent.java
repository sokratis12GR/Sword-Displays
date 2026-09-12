package com.sofodev.sworddisplay.data;

import com.sofodev.sworddisplay.registry.ModBlocks;
import com.sofodev.sworddisplay.registry.RegistryHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;

@EventBusSubscriber(modid = MODID)
public class DataGeneratorEvent {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        PackOutput packOutput = generator.getPackOutput();
        // Client-side data: models & blockstates
        if (event.includeClient()) {
            generator.addProvider(true, new SwordDisplayModelProvider(packOutput,
                    event.getExistingFileHelper(),
                    new RegistryHelper(ModBlocks.REGISTRY_LIST)));
        }

        // Server-side data: recipes
        if (event.includeServer()) {
            generator.addProvider(true, new Recipes(generator, provider));
            generator.addProvider(true, new ModBlockTagProvider(packOutput, provider, event.getExistingFileHelper()));
            generator.addProvider(true, new ModLootTableProvider(packOutput, provider));
            generator.addProvider(true, new ModLanguageProvider(packOutput));
        }
    }
}