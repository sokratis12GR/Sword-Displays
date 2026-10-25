package com.sofodev.sworddisplay.blocks;

import com.sofodev.sworddisplay.config.SDConfig;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class SDBlockItem extends BlockItem {
    public SDBlockItem(Block block, Properties properties) { super(block, properties); }

    @Override
    public boolean isEnabled(net.minecraft.world.flag.FeatureFlagSet flags) {
        return SDConfig.isEnabled(getBlock());
    }
}
