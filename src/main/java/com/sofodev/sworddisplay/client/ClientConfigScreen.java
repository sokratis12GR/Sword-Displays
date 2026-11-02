package com.sofodev.sworddisplay.client;

import com.sofodev.sworddisplay.config.SDConfig;
import com.sofodev.thedragonlib.client.config.ConfigScreenHelper;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fml.ModContainer;

public final class ClientConfigScreen {
    public static void register(ModContainer modContainer) {
        ConfigScreenHelper.registerConfigScreen(modContainer, Component.translatable("sworddisplay.configuration.title"), SDConfig.SPEC);
    }

    private ClientConfigScreen() {}
}
