package com.outsiderh.tfcworldmap.client;

import com.outsiderh.tfcworldmap.client.collection.FixedRect;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public abstract class GuiRenderer {
    public static void box(GuiGraphics guiGraphics, FixedRect pos, int color) {
        guiGraphics.hLine(pos.x(), pos.x2(), pos.y(), color);
        guiGraphics.hLine(pos.x(), pos.x2(), pos.y2(), color);
        guiGraphics.vLine(pos.x(), pos.y(), pos.y2(), color);
        guiGraphics.vLine(pos.x2(), pos.y(), pos.y2(), color);
    }
    public static void sprite(GuiGraphics guiGraphics, ResourceLocation atlas, int x, int y, FixedRect spriteRect) {
        guiGraphics.blit(atlas, x, y, spriteRect.x(), spriteRect.y(), spriteRect.w(), spriteRect.h());
    }
}
