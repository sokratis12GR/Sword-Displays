package com.sofodev.sworddisplay.data;

import com.sofodev.sworddisplay.SwordDisplay;
import com.sofodev.sworddisplay.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static com.sofodev.sworddisplay.registry.ModBlocks.registryHelper;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, SwordDisplay.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Creative tab
        add("tabs.sworddisplay.core", "Sword Displays");

        List<ModBlocks.BlockRegistryEntry> entries = registryHelper.getRegistryList(); // assuming getter exists
        for (ModBlocks.BlockRegistryEntry entry : entries) {
            RegistryObject<Block> caseBlock = entry.blocks().caseBlock();
            RegistryObject<Block> displayBlock = entry.blocks().displayBlock();
            RegistryObject<Block> wallBlock = entry.blocks().wallDisplay();
            add(caseBlock.get(), toTitleCase(caseBlock.getKey().location().getPath()));
            add(displayBlock.get(), toTitleCase(displayBlock.getKey().location().getPath()));
            add(wallBlock.get(), toTitleCase(wallBlock.getKey().location().getPath()));
        }
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