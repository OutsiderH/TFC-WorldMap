package com.outsiderh.tfcworldmap.client.page;

import com.outsiderh.tfcworldmap.client.collection.FixedRect;

import net.minecraft.client.gui.GuiGraphics;

public class PaperAndInkPage extends AtlasPage {
    private static final FixedRect buttonSpriteRect = new FixedRect(1, 182, 16, 16);
    public PaperAndInkPage() {
        super(true);
    }
    @Override
    public FixedRect getButtonSpriteRect() {
        return buttonSpriteRect;
    }
    @Override
    public void render(GuiGraphics guiGraphics, FixedRect renderPosition) {
        super.render(guiGraphics, renderPosition);
    }
}
