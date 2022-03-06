package com.sofodev.sworddisplay.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.UUID;

import static com.sofodev.sworddisplay.SwordDisplay.RegistryEvents.SWORD_DISPLAY_TYPE;
import static net.minecraft.nbt.Tag.TAG_COMPOUND;

public class SwordDisplayTile extends BaseTile {

    private ItemStack cachedSword;
    private UUID owner;

    public SwordDisplayTile(BlockPos pos, BlockState state) {
        super(SWORD_DISPLAY_TYPE.get(), pos, state);
        this.cachedSword = ItemStack.EMPTY;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("displayed_item", TAG_COMPOUND)) {
            this.cachedSword = ItemStack.of(tag.getCompound("displayed_item"));
        }
        if (tag.hasUUID("owner")) {
            this.owner = tag.getUUID("owner");
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("displayed_item", this.cachedSword.save(new CompoundTag()));
        if (this.owner != null) {
            tag.putUUID("owner", this.owner);
        }
        super.saveAdditional(tag);
    }

    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    //TODO: TEST EXTENSIVELY
    @Override
    @Nullable
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    public boolean onlyOpCanSetNbt() {
        return false;
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        return super.triggerEvent(id, type);
    }

    @Nullable
    @Override
    public Level getLevel() {
        return super.getLevel();
    }

    @Override
    public BlockPos getBlockPos() {
        return super.getBlockPos();
    }

    public ItemStack getSword() {
        return this.cachedSword;
    }

    public void setSword(ItemStack stack) {
        this.cachedSword = stack;
        this.sendBlockUpdate();
    }

    public UUID getOwner() {
        return this.owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
        this.sendBlockUpdate();
    }

    public void sendBlockUpdate() {
        this.setChanged();
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }
}