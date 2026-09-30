package com.sofodev.sworddisplay.data.client;

import com.sofodev.sworddisplay.SwordDisplay;
import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class SDLanguageProvider extends LanguageProvider {
    public SDLanguageProvider(PackOutput output) {
        super(output, SwordDisplay.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (ModBlocks.BlockRegistryEntry entry : ModBlocks.REGISTRY_LIST) {
            add(entry.blocks().displayBlock().get(), title(entry.key()) + " Sword Display");
            add(entry.blocks().caseBlock().get(), title(entry.key()) + " Sword Case");
            add(entry.blocks().wallDisplay().get(), title(entry.key()) + " Wall Display");
        }

        add("tabs.sworddisplay.core", "Sword Displays");
    }

    private static String title(String key) {
        return Arrays.stream(key.split("_"))
                .map(part -> Character.toUpperCase(part.charAt(0)) + part.substring(1))
                .collect(Collectors.joining(" "));
    }
}
