package com.sofodev.sworddisplay.data.client;

import net.neoforged.neoforge.data.event.GatherDataEvent;

public final class SDClientData {
    private SDClientData() {
    }

    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(SDModelProvider::new);
        event.createProvider(SDLanguageProvider::new);
    }
}
