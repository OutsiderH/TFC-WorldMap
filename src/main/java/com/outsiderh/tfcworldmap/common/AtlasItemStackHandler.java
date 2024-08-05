package com.outsiderh.tfcworldmap.common;

import com.outsiderh.tfcworldmap.common.item.AtlasItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class AtlasItemStackHandler extends ItemStackHandler {
    private final ItemStack atlasStack;
    public AtlasItemStackHandler(ItemStack atlasStack) {
        super(2);
        this.atlasStack = atlasStack;
    }
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        stacks.set(0, ItemStack.of(nbt.getCompound(AtlasItem.containedItem0Key)));
        stacks.set(1, ItemStack.of(nbt.getCompound(AtlasItem.containedItem1Key)));
        onLoad();
    }
    @SuppressWarnings("null")
    @Override
    protected void onContentsChanged(int slot) {
        final CompoundTag tag = atlasStack.getTag();
        switch (slot) {
            case 0:
                tag.put(AtlasItem.containedItem0Key, stacks.get(slot).save(new CompoundTag()));
                return;
            case 1:
                tag.put(AtlasItem.containedItem1Key, stacks.get(slot).save(new CompoundTag()));
                return;
        }
    }
}
