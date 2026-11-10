package com.sofodev.sworddisplay.client;

import com.sofodev.thedragonlib.client.config.ConfigScreenHelper;
import net.neoforged.fml.ModContainer;

public final class ClientConfigScreen {
    public static void register(ModContainer modContainer) {
        ConfigScreenHelper.registerDefaultScreen(modContainer);
    }

    private ClientConfigScreen() {}
}
