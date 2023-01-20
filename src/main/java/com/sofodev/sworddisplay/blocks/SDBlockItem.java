package com.sofodev.sworddisplay.blocks;

import com.sofodev.sworddisplay.SwordDisplay;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class SDBlockItem extends BlockItem {

    public SDBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    public SDBlockItem(Block blockIn) {
        super(blockIn, new Properties());
    }
}