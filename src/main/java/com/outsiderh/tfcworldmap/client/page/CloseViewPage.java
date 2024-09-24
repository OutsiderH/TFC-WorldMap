package com.outsiderh.tfcworldmap.client.page;

import com.outsiderh.tfcworldmap.client.GuiRenderer;
import com.outsiderh.tfcworldmap.client.collection.FixedRect;
import com.outsiderh.tfcworldmap.common.save.AtlasData;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class CloseViewPage extends AtlasPage {
    private static final FixedRect buttonSpriteRect = new FixedRect(18, 182, 16, 16);
    public CloseViewPage() {
        super(false);
    }
    @Override
    public FixedRect getButtonSpriteRect() {
        return buttonSpriteRect;
    }
    @Override
    public void render(GuiGraphics guiGraphics, FixedRect renderPosition, AtlasData data) {
        super.render(guiGraphics, renderPosition, data);
        GuiRenderer.text(guiGraphics, Component.literal(data.get()), renderPosition.x(), renderPosition.y(), 0xFF000000);
    }
}
