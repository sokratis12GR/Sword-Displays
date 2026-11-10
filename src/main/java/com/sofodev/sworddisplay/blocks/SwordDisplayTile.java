package com.sofodev.sworddisplay.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
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

import static com.sofodev.sworddisplay.registry.ModBlocks.SWORD_DISPLAY_TYPE;
import static net.minecraft.nbt.Tag.TAG_COMPOUND;

public class SwordDisplayTile extends BaseTile {

    private ItemStack cachedSword;
    private UUID owner;
    private final ItemStack[] additionalWeapons = {ItemStack.EMPTY, ItemStack.EMPTY};
    private final int[] rackHorizontalRotations = new int[3];
    private final int[] rackTiltRotations = new int[3];

    public SwordDisplayTile(BlockPos pos, BlockState state) {
        super(SWORD_DISPLAY_TYPE.get(), pos, state);
        this.cachedSword = ItemStack.EMPTY;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        if (tag.contains("displayed_item", TAG_COMPOUND)) {
            this.cachedSword = ItemStack.parseOptional(provider, tag.getCompound("displayed_item"));
        }
        if (tag.hasUUID("owner")) {
            this.owner = tag.getUUID("owner");
        }
        if (tag.contains("displayed_item_2", TAG_COMPOUND)) {
            this.additionalWeapons[0] = ItemStack.parseOptional(provider, tag.getCompound("displayed_item_2"));
        }
        if (tag.contains("displayed_item_3", TAG_COMPOUND)) {
            this.additionalWeapons[1] = ItemStack.parseOptional(provider, tag.getCompound("displayed_item_3"));
        }
        for (int i = 0; i < 3; i++) {
            rackHorizontalRotations[i] = Math.floorMod(tag.getInt("rack_horizontal_" + i), 8);
            rackTiltRotations[i] = Math.floorMod(tag.getInt("rack_tilt_" + i), 8);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.put("displayed_item", this.cachedSword.saveOptional(provider));
        if (this.owner != null) {
            tag.putUUID("owner", this.owner);
        }
        if (!additionalWeapons[0].isEmpty()) tag.put("displayed_item_2", additionalWeapons[0].saveOptional(provider));
        if (!additionalWeapons[1].isEmpty()) tag.put("displayed_item_3", additionalWeapons[1].saveOptional(provider));
        if (isRack()) for (int i = 0; i < 3; i++) {
            tag.putInt("rack_horizontal_" + i, rackHorizontalRotations[i]);
            tag.putInt("rack_tilt_" + i, rackTiltRotations[i]);
        }
        super.saveAdditional(tag, provider);
    }


    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        return this.saveWithFullMetadata(provider);
    }

    @Override
    @Nullable
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookup) {
        this.loadCustomOnly(pkt.getTag(), lookup);
    }


    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider holders) {
        super.handleUpdateTag(tag, holders);
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

    /** Slots 1 and 2 are only used by rack blocks; slot 0 keeps the original save key. */
    public ItemStack getWeapon(int slot) {
        if (slot < 0 || slot > 2) return ItemStack.EMPTY;
        return slot == 0 ? cachedSword : additionalWeapons[slot - 1];
    }

    public void setWeapon(int slot, ItemStack weapon) {
        if (slot < 0 || slot > 2) return;
        if (slot == 0) { setSword(weapon); return; }
        additionalWeapons[slot - 1] = weapon;
        sendBlockUpdate();
    }

    public ItemStack getSword() {
        return this.cachedSword;
    }

    public void setSword(ItemStack stack) {
        this.cachedSword = stack;
        this.sendBlockUpdate();
    }

    public boolean isRack() {
        return getBlockState().getBlock() instanceof RackDisplayBlock
                || getBlockState().getBlock() instanceof RackCaseBlock
                || getBlockState().getBlock() instanceof RackWallBlock;
    }

    public int getRackHorizontalRotation(int slot) { return rackHorizontalRotations[Math.max(0, Math.min(2, slot))]; }
    public int getRackTiltRotation(int slot) { return rackTiltRotations[Math.max(0, Math.min(2, slot))]; }

    public void rotateRackHorizontal(int slot) {
        if (slot < 0 || slot > 2) return;
        rackHorizontalRotations[slot] = (rackHorizontalRotations[slot] + 1) % 8;
        sendBlockUpdate();
    }

    public void rotateRackTilt(int slot) {
        if (slot < 0 || slot > 2) return;
        rackTiltRotations[slot] = (rackTiltRotations[slot] + 1) % 8;
        sendBlockUpdate();
    }

    public boolean consumeHorizontalClick(UUID playerId) {
        return false;
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
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
    }
}