package com.outsiderh.tfcworldmap.common.menu;

import javax.annotation.Nonnull;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class Menu extends AbstractContainerMenu {
    protected Menu(int id, MenuType<?> type, Inventory inventory) {
        super(type, id);
    }
    @Override
    public ItemStack quickMoveStack(@Nonnull Player player, int pIndex) {
        return ItemStack.EMPTY;
    }
    @Override
    public boolean stillValid(@Nonnull Player player) {
        return true;
    }
}
