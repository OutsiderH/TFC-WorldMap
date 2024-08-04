package com.outsiderh.tfcworldmap.common.menu;

import com.outsiderh.tfcworldmap.client.AtlasScreen;
import com.outsiderh.tfcworldmap.common.HideableSlot;
import com.outsiderh.tfcworldmap.common.Main;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class AtlasMenu extends ItemStackMenu {
    public static AtlasMenu create(int id, Inventory inventory, InteractionHand usedHand, int usedSlot, ItemStack usedStack) {
        return new AtlasMenu(id, inventory, usedHand, usedSlot, usedStack);
    }
    protected AtlasMenu(int id, Inventory inventory, InteractionHand usedHand, int usedSlot, ItemStack usedStack) {
        super(id, Main.RegistryObjects.atlasMenu.get(), inventory, usedHand, usedSlot, usedStack);
        for (int i = 0; i < 9; ++i) {
            addSlot(new HideableSlot(inventory, i, AtlasScreen.pageRenderPositionLocal.x() + 18 * i + 1, AtlasScreen.pageRenderPositionLocal.y2() - 16));
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlot(new HideableSlot(inventory, 9 + j + i * 9, AtlasScreen.pageRenderPositionLocal.x() + 18 * j + 1, AtlasScreen.pageRenderPositionLocal.y2() - 18 * i - 37));
            }
        }
    }
    public ItemStack getAtlasStack() {
        return usedStack;
    }
}
