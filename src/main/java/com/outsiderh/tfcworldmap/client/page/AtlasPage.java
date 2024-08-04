package com.outsiderh.tfcworldmap.client.page;

import com.outsiderh.tfcworldmap.client.GuiRenderer;
import com.outsiderh.tfcworldmap.client.collection.FixedRect;

import net.minecraft.client.gui.GuiGraphics;

public abstract class AtlasPage {
    public final boolean shouldShowInventory;
    protected AtlasPage(boolean shouldShowInventory) {
        this.shouldShowInventory = shouldShowInventory;
    }
    public FixedRect getButtonSpriteRect() {
        return null;
    }
    public void render(GuiGraphics guiGraphics, FixedRect renderPosition) {
        GuiRenderer.box(guiGraphics, renderPosition, 0xFF00FF00);
    }
}
