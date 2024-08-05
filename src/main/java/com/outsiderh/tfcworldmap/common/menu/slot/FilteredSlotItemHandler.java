package com.outsiderh.tfcworldmap.common.menu.slot;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FilteredSlotItemHandler extends SlotItemHandler implements IHideable {
    private boolean active;
    private final Item[] acceptedItem;
    public FilteredSlotItemHandler(IItemHandler itemHandler, int index, int x, int y, Item[] acceptedItem) {
        super(itemHandler, index, x, y);
        active = true;
        this.acceptedItem = acceptedItem;
    }
    @Override
    public boolean isActive() {
        return active;
    }
    @Override
    public boolean mayPlace(ItemStack stack) {
        for (Item item : acceptedItem) {
            if (stack.is(item)) {
                return super.mayPlace(stack);
            }
        }
        return false;
    }
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
