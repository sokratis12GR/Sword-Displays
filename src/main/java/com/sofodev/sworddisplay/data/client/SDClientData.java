package com.sofodev.sworddisplay.data.client;

import net.minecraftforge.data.event.GatherDataEvent;

public final class SDClientData {
    private SDClientData() {
    }

    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();

        generator.addProvider(
                event.includeClient(),
                new SDModelProvider(generator.getPackOutput())
        );

        generator.addProvider(
                event.includeClient(),
                new SDLanguageProvider(generator.getPackOutput())
        );
    }
}