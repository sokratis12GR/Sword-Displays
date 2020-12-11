package com.sofodev.sworddisplay.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.UUID;

import static com.sofodev.sworddisplay.SwordDisplay.RegistryEvents.SWORD_DISPLAY_TYPE;
import static net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND;

public class SwordDisplayTile extends BaseTile {

    private ItemStack cachedSword;
    private UUID owner;

    public SwordDisplayTile() {
        super(SWORD_DISPLAY_TYPE.get());
        this.cachedSword = ItemStack.EMPTY;
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.loadFromNBT(nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        this.saveToNbt(compound);
        return compound;
    }

    public CompoundNBT saveToNbt(CompoundNBT compound) {
        compound.put("displayed_item", this.cachedSword.write(new CompoundNBT()));
        if (this.owner != null) {
            compound.putUniqueId("owner", this.owner);
        }
        return compound;
    }

    public void loadFromNBT(CompoundNBT compound) {
        if (compound.contains("displayed_item", TAG_COMPOUND)) {
            this.cachedSword = ItemStack.read(compound.getCompound("displayed_item"));
        }
        if (compound.hasUniqueId("owner")) {
            this.owner = compound.getUniqueId("owner");
        }
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 12, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        super.handleUpdateTag(state, tag);
    }

    @Override
    public boolean onlyOpsCanSetNbt() {
        return false;
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        return super.receiveClientEvent(id, type);
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public BlockPos getPos() {
        return this.pos;
    }

    public ItemStack getSword() {
        return this.cachedSword;
    }

    public UUID getOwner() {
        return this.owner;
    }

    public void setSword(ItemStack stack) {
        this.cachedSword = stack;
        this.markAndUpdate();
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
        this.markAndUpdate();
    }

    public void markAndUpdate() {
        this.markDirty();
        this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
    }
}