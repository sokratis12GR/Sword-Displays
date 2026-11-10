package com.sofodev.sworddisplay.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.minecraft.world.level.block.Block;
import com.sofodev.sworddisplay.blocks.*;

public final class SDConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ORIGINAL_ENABLED;
    public static final ModConfigSpec.BooleanValue ORIGINAL_DISPLAY;
    public static final ModConfigSpec.BooleanValue ORIGINAL_CASE;
    public static final ModConfigSpec.BooleanValue ORIGINAL_WALL;

    public static final ModConfigSpec.BooleanValue PLAQUE_ENABLED;
    public static final ModConfigSpec.BooleanValue PLAQUE_DISPLAY;
    public static final ModConfigSpec.BooleanValue PLAQUE_CASE;
    public static final ModConfigSpec.BooleanValue PLAQUE_WALL;

    public static final ModConfigSpec.BooleanValue RACK_ENABLED;
    public static final ModConfigSpec.BooleanValue RACK_DISPLAY;
    public static final ModConfigSpec.BooleanValue RACK_CASE;
    public static final ModConfigSpec.BooleanValue RACK_WALL;

    static {
        BUILDER.translation("sworddisplay.configuration.original").push("original");
        ORIGINAL_ENABLED = familyEnabled("original");
        ORIGINAL_DISPLAY = enabled("original", "display");
        ORIGINAL_CASE = enabled("original", "case");
        ORIGINAL_WALL = enabled("original", "wall");
        BUILDER.pop();

        BUILDER.translation("sworddisplay.configuration.plaque").push("plaque");
        PLAQUE_ENABLED = familyEnabled("plaque");
        PLAQUE_DISPLAY = enabled("plaque", "display");
        PLAQUE_CASE = enabled("plaque", "case");
        PLAQUE_WALL = enabled("plaque", "wall");
        BUILDER.pop();

        BUILDER.translation("sworddisplay.configuration.rack").push("rack");
        RACK_ENABLED = familyEnabled("rack");
        RACK_DISPLAY = enabled("rack", "display");
        RACK_CASE = enabled("rack", "case");
        RACK_WALL = enabled("rack", "wall");
        BUILDER.pop();
    }

    public static final ModConfigSpec SPEC = BUILDER.build();

    private static ModConfigSpec.BooleanValue familyEnabled(String family) {
        return BUILDER.translation("sworddisplay.configuration." + family + ".enabled")
                .comment("Enable all " + family + " blocks and items. Overrides the individual options below (restart required).")
                .define("enabled", true);
    }

    private static ModConfigSpec.BooleanValue enabled(String family, String type) {
        return BUILDER.translation("sworddisplay.configuration." + family + "." + type)
                .comment("Enable " + family + " " + type + " blocks and items (restart required).")
                .define(type, true);
    }

    public static boolean isEnabled(Block block) {
        if (!SPEC.isLoaded()) return true;
        if (block instanceof RackWallBlock) return RACK_ENABLED.get() && RACK_WALL.get();
        if (block instanceof RackCaseBlock) return RACK_ENABLED.get() && RACK_CASE.get();
        if (block instanceof RackDisplayBlock) return RACK_ENABLED.get() && RACK_DISPLAY.get();
        if (block instanceof PlaqueWallBlock) return PLAQUE_ENABLED.get() && PLAQUE_WALL.get();
        if (block instanceof PlaqueCaseBlock) return PLAQUE_ENABLED.get() && PLAQUE_CASE.get();
        if (block instanceof PlaqueDisplayBlock) return PLAQUE_ENABLED.get() && PLAQUE_DISPLAY.get();
        if (block instanceof SwordWallDisplayBlock) return ORIGINAL_ENABLED.get() && ORIGINAL_WALL.get();
        if (block instanceof SwordCaseBlock) return ORIGINAL_ENABLED.get() && ORIGINAL_CASE.get();
        if (block instanceof SwordDisplayBlock) return ORIGINAL_ENABLED.get() && ORIGINAL_DISPLAY.get();
        return true;
    }

    private SDConfig() {}
}
