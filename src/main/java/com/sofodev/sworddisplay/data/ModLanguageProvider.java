package com.sofodev.sworddisplay.data;

import com.sofodev.sworddisplay.SwordDisplay;
import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

import static com.sofodev.sworddisplay.registry.ModBlocks.registryHelper;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, SwordDisplay.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Creative tabs
        add("tabs.sworddisplay.core", "Sword Displays");
        add("tabs.sworddisplay.plaques", "Weapon Plaques");
        add("tabs.sworddisplay.racks", "Weapon Racks");

        // Configuration screen
        String config = "sworddisplay.configuration.";
        add(config + "title", "Sword Displays Configuration");
        add(config + "section.sworddisplay.common.toml", "Common Settings");
        add(config + "section.sworddisplay.common.toml.title", "Sword Displays Common Settings");
        addConfigFamily("original", "Original Displays");
        addConfigFamily("plaque", "Weapon Plaques");
        addConfigFamily("rack", "Weapon Racks");

        List<ModBlocks.BlockRegistryEntry> entries = registryHelper.getRegistryList(); // assuming getter exists
        for (ModBlocks.BlockRegistryEntry entry : entries) {
            DeferredHolder<Block, Block> caseBlock = entry.blocks().caseBlock();
            DeferredHolder<Block, Block> displayBlock = entry.blocks().displayBlock();
            DeferredHolder<Block, Block> wallBlock = entry.blocks().wallDisplay();
            add(caseBlock.get(), toTitleCase(entry.key()) + " Sword Case");
            add(displayBlock.get(), toTitleCase(entry.key()) + " Sword Display");
            add(wallBlock.get(), toTitleCase(entry.key()) + " Wall Display");
        }

        for (ModBlocks.StyledBlockEntry entry : ModBlocks.STYLED_ENTRIES) {
            String stylePrefix = toTitleCase(entry.style());
            DeferredHolder<Block, Block> caseBlock = entry.blocks().caseBlock();
            DeferredHolder<Block, Block> displayBlock = entry.blocks().displayBlock();
            DeferredHolder<Block, Block> wallBlock = entry.blocks().wallDisplay();
            add(caseBlock.get(), stylePrefix + " " + toTitleCase(entry.key()) + " Sword Case");
            add(displayBlock.get(), stylePrefix + " " + toTitleCase(entry.key()) + " Sword Display");
            add(wallBlock.get(), stylePrefix + " " + toTitleCase(entry.key()) + " Wall Display");
        }
    }

    private void addConfigFamily(String family, String name) {
        String key = "sworddisplay.configuration." + family;
        add(key, name);
        add(key + ".button", "Configure " + name);
        add(key + ".tooltip", "Settings for " + name + ".");
        add(key + ".enabled", "Enable All " + name);
        add(key + ".enabled.tooltip", "Enable all " + name + ". Overrides the individual options below (restart required).");
        addConfigType(key, "display", "Sword Displays", name);
        addConfigType(key, "case", "Sword Cases", name);
        addConfigType(key, "wall", "Wall Displays", name);
    }

    private void addConfigType(String familyKey, String type, String typeName, String familyName) {
        add(familyKey + "." + type, typeName);
        add(familyKey + "." + type + ".tooltip", "Enable " + typeName + " for " + familyName + " (restart required).");
    }

    private static String toTitleCase(String input) {
        String[] parts = input.split("_");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                result.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1))
                        .append(" ");
            }
        }
        return result.toString().trim();
    }
}