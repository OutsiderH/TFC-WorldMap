package com.outsiderh.tfcworldmap.common.menu;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.outsiderh.tfcworldmap.client.AtlasScreen;
import com.outsiderh.tfcworldmap.common.AtlasItemStackHandler;
import com.outsiderh.tfcworldmap.common.Main;
import com.outsiderh.tfcworldmap.common.item.AtlasItem;
import com.outsiderh.tfcworldmap.common.menu.slot.FilteredSlotItemHandler;
import com.outsiderh.tfcworldmap.common.menu.slot.HideableSlot;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class AtlasMenu extends ItemStackMenu {
    private final AtlasItemStackHandler itemStackHandler;
    public static AtlasMenu create(int id, Inventory inventory, InteractionHand usedHand, int usedSlot, ItemStack usedStack, @Nullable CompoundTag extraData) {
        return new AtlasMenu(id, inventory, usedHand, usedSlot, usedStack, extraData);
    }
    protected AtlasMenu(int id, Inventory inventory, InteractionHand usedHand, int usedSlot, ItemStack usedStack, @Nullable CompoundTag extraData) {
        super(id, Main.RegistryObjects.atlasMenu.get(), inventory, usedHand, usedSlot, usedStack, extraData);
        itemStackHandler = AtlasItem.getItems(usedStack);
        itemStackHandler.deserializeNBT(usedStack.getTag());
        for (int i = 0; i < 9; ++i) {
            addSlot(new HideableSlot(inventory, i, AtlasScreen.pageRenderPositionLocal.x() + 18 * i + 1, AtlasScreen.pageRenderPositionLocal.y2() - 16));
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlot(new HideableSlot(inventory, 9 + j + i * 9, AtlasScreen.pageRenderPositionLocal.x() + 18 * j + 1, AtlasScreen.pageRenderPositionLocal.y2() + 18 * i - 73));
            }
        }
        addSlot(new FilteredSlotItemHandler(itemStackHandler, 0, 78, 31, new Item[]{Items.PAPER}));
        addSlot(new FilteredSlotItemHandler(itemStackHandler, 1, 96, 31, new Item[]{Items.INK_SAC}));
    }
    public ItemStack getAtlasStack() {
        return usedStack;
    }
    @Override
    public ItemStack quickMoveStack(@Nonnull Player player, int pIndex) {
        return ItemStack.EMPTY;
    }
}
