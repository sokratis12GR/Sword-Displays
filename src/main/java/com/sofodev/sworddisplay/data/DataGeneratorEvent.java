package com.sofodev.sworddisplay.data;

import com.sofodev.sworddisplay.registry.ModBlocks;
import com.sofodev.sworddisplay.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.sofodev.sworddisplay.SwordDisplay.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneratorEvent {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        // Client-side data: models & blockstates
        if (event.includeClient()) {
            generator.addProvider(true, new SwordDisplayModelProvider(generator.getPackOutput(),
                    event.getExistingFileHelper(),
                    new RegistryHelper(ModBlocks.REGISTRY_LIST)));
        }

        // Server-side data: recipes
        if (event.includeServer()) {
            generator.addProvider(true, new Recipes(generator));
            generator.addProvider(true, new ModLootTableProvider(generator.getPackOutput()));
            generator.addProvider(true, new ModLanguageProvider(generator.getPackOutput()));
        }
    }
}