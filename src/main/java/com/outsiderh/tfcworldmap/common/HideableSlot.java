package com.outsiderh.tfcworldmap.common;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

public class HideableSlot extends Slot {
    private boolean active;
    public HideableSlot(Container container, int index, int x, int y) {
        super(container, index, x, y);
        active = true;
    }
    @Override
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
