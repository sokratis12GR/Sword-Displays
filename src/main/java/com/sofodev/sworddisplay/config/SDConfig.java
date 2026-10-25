package com.sofodev.sworddisplay.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.minecraft.world.level.block.Block;
import com.sofodev.sworddisplay.blocks.*;

public final class SDConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ORIGINAL_DISPLAY = enabled("original", "display");
    public static final ModConfigSpec.BooleanValue ORIGINAL_CASE = enabled("original", "case");
    public static final ModConfigSpec.BooleanValue ORIGINAL_WALL = enabled("original", "wall");
    public static final ModConfigSpec.BooleanValue PLAQUE_DISPLAY = enabled("plaque", "display");
    public static final ModConfigSpec.BooleanValue PLAQUE_CASE = enabled("plaque", "case");
    public static final ModConfigSpec.BooleanValue PLAQUE_WALL = enabled("plaque", "wall");
    public static final ModConfigSpec.BooleanValue RACK_DISPLAY = enabled("rack", "display");
    public static final ModConfigSpec.BooleanValue RACK_CASE = enabled("rack", "case");
    public static final ModConfigSpec.BooleanValue RACK_WALL = enabled("rack", "wall");

    public static final ModConfigSpec SPEC = BUILDER.build();

    private static ModConfigSpec.BooleanValue enabled(String family, String type) {
        return BUILDER.comment("Enable " + family + " " + type + " blocks and items (restart required).")
                .define(family + "." + type, true);
    }

    public static boolean isEnabled(Block block) {
        if (block instanceof RackWallBlock) return RACK_WALL.get();
        if (block instanceof RackCaseBlock) return RACK_CASE.get();
        if (block instanceof RackDisplayBlock) return RACK_DISPLAY.get();
        if (block instanceof PlaqueWallBlock) return PLAQUE_WALL.get();
        if (block instanceof PlaqueCaseBlock) return PLAQUE_CASE.get();
        if (block instanceof PlaqueDisplayBlock) return PLAQUE_DISPLAY.get();
        if (block instanceof SwordWallDisplayBlock) return ORIGINAL_WALL.get();
        if (block instanceof SwordCaseBlock) return ORIGINAL_CASE.get();
        if (block instanceof SwordDisplayBlock) return ORIGINAL_DISPLAY.get();
        return true;
    }

    private SDConfig() {}
}
