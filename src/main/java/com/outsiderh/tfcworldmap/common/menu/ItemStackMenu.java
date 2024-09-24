package com.outsiderh.tfcworldmap.common.menu;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class ItemStackMenu extends Menu {
    protected final InteractionHand usedHand;
    protected final int usedSlot;
    protected final ItemStack usedStack;
    @Nullable protected CompoundTag extraData;
    protected ItemStackMenu(int id, MenuType<?> type, Inventory inventory, InteractionHand usedHand, int usedSlot, ItemStack usedStack, @Nullable CompoundTag extraData) {
        super(id, type, inventory);
        this.usedHand = usedHand;
        this.usedSlot = usedSlot;
        this.usedStack = usedStack;
        this.extraData = extraData;
    }
    public @Nullable CompoundTag getExtraData() {
        return extraData;
    }
    @Override
    public void clicked(int slot, int button, @Nonnull ClickType clickType, @Nonnull Player player) {
        if (slot != usedSlot) {
            super.clicked(slot, button, clickType, player);
        }
    }
    @Override
    public boolean stillValid(@Nonnull Player player) {
        return !player.getItemInHand(usedHand).isEmpty();
    }
    @FunctionalInterface
    public interface Factory<T extends ItemStackMenu> {
        T create(int id, Inventory inventory, InteractionHand usedHand, int usedSlot, ItemStack usedStack, @Nullable CompoundTag extraData);
    }
}
