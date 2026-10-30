package com.sofodev.sworddisplay.data.client;

import com.sofodev.sworddisplay.SwordDisplay;
import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

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

        for (ModBlocks.StyledBlockEntry entry : ModBlocks.STYLED_ENTRIES) {
            String styleName = switch (entry.style()) {
                case "plaque" -> "Plaque";
                case "rack" -> "Rack";
                default -> title(entry.style());
            };
            String name = styleName + " " + title(entry.key());
            add(entry.blocks().displayBlock().get(), name + " Sword Display");
            add(entry.blocks().caseBlock().get(), name + " Sword Case");
            add(entry.blocks().wallDisplay().get(), name + " Wall Display");
        }
        add("tabs.sworddisplay.core", "Sword Displays");
        add("tabs.sworddisplay.plaques", "Weapon Plaques");
        add("tabs.sworddisplay.racks", "Weapon Racks");

        addConfigFamily("original", "Original", "original");
        addConfigFamily("plaque", "Plaques", "plaque");
        addConfigFamily("rack", "Racks", "rack");
    }

    private void addConfigFamily(String family, String label, String adjective) {
        add("sworddisplay.configuration." + family, label);
        add("sworddisplay.configuration." + family + ".enabled", "Enable All " + label);
        add("sworddisplay.configuration." + family + ".enabled.tooltip",
                "Enable all " + adjective + " blocks and items. Overrides the individual options below (restart required).");
        add("sworddisplay.configuration." + family + ".display", "Enable Displays");
        add("sworddisplay.configuration." + family + ".display.tooltip",
                "Enable " + adjective + " display blocks and items (restart required).");
        add("sworddisplay.configuration." + family + ".case", "Enable Cases");
        add("sworddisplay.configuration." + family + ".case.tooltip",
                "Enable " + adjective + " case blocks and items (restart required).");
        add("sworddisplay.configuration." + family + ".wall", "Enable Wall Displays");
        add("sworddisplay.configuration." + family + ".wall.tooltip",
                "Enable " + adjective + " wall display blocks and items (restart required).");
    }

    private static String title(String key) {
        return Arrays.stream(key.split("_"))
                .map(part -> Character.toUpperCase(part.charAt(0)) + part.substring(1))
                .collect(Collectors.joining(" "));
    }
}
