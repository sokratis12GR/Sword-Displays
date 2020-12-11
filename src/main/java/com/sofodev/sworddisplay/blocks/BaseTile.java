package com.sofodev.sworddisplay.blocks;

import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.minecraftforge.energy.CapabilityEnergy.ENERGY;
import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

/**
 * @author Sokratis Fotkatzikis
 **/
public abstract class BaseTile extends TileEntity {

    public BaseTile(TileEntityType<?> type) {
        super(type);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ITEM_HANDLER_CAPABILITY) {
            IItemHandler handler = this.getItemHandler(side);
            if (handler != null) return LazyOptional.of(() -> handler).cast();
        } else if (cap == FLUID_HANDLER_CAPABILITY) {
            IFluidHandler tank = this.getFluidHandler(side);
            if (tank != null) return LazyOptional.of(() -> tank).cast();
        } else if (cap == ENERGY) {
            IEnergyStorage storage = this.getEnergyStorage(side);
            if (storage != null) return LazyOptional.of(() -> storage).cast();
        }
        return super.getCapability(cap, side);
    }

    public IFluidHandler getFluidHandler(Direction direction) {
        return null;
    }

    public IEnergyStorage getEnergyStorage(Direction direction) {
        return null;
    }

    public IItemHandler getItemHandler(Direction direction) {
        return null;
    }
}