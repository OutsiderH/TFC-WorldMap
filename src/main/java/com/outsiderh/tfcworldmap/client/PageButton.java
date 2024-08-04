package com.outsiderh.tfcworldmap.client;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.systems.RenderSystem;
import com.outsiderh.tfcworldmap.client.collection.FixedRect;
import com.outsiderh.tfcworldmap.client.page.AtlasPage;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

public class PageButton extends ImageButton {
    private final AtlasPage page;
    public static PageButton create(ResourceLocation atlas, int x, int y, AtlasPage page, OnPress onPress) {
        return new PageButton(atlas, x, y, page.getButtonSpriteRect(), page, onPress);
    }
    private PageButton(ResourceLocation atlas, int x, int y, FixedRect spriteRect, AtlasPage page, OnPress onPress) {
        super(x, y, spriteRect.w(), spriteRect.h(), spriteRect.x(), spriteRect.y(), spriteRect.h() + 1, atlas, onPress);
        this.page = page;
    }
    public AtlasPage getPage() {
        return page;
    }
    @Override
    public void playDownSound(@Nonnull SoundManager soundManager) {
        soundManager.play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0f));
    }
    @Override
    public void renderWidget(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int spritePosY = yTexStart;
        int renderPosY = getY();
        if (isHovered) {
            spritePosY = spritePosY + yDiffTex;
            ++renderPosY;
        }
        RenderSystem.enableDepthTest();
        GuiRenderer.sprite(guiGraphics, resourceLocation, getX(), renderPosY, new FixedRect(xTexStart, spritePosY, width, height));
    }
}
