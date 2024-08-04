package com.outsiderh.tfcworldmap.client;

import javax.annotation.Nonnull;

import com.outsiderh.tfcworldmap.client.collection.FixedRect;
import com.outsiderh.tfcworldmap.client.collection.Rect;
import com.outsiderh.tfcworldmap.client.page.AtlasPage;
import com.outsiderh.tfcworldmap.client.page.AtlasPages;
import com.outsiderh.tfcworldmap.client.page.PaperAndInkPage;
import com.outsiderh.tfcworldmap.common.HideableSlot;
import com.outsiderh.tfcworldmap.common.menu.AtlasMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class AtlasScreen extends AbstractContainerScreen<AtlasMenu> {
    public static final FixedRect pageRenderPositionLocal = new FixedRect(13, 11, 162, 147);
    private static final ResourceLocation atlasTextureAtlas = new ResourceLocation("tfcworldmap", "textures/gui/atlas.png");
    private static final FixedRect atlasRect = new FixedRect(1, 1, 190, 180);
    private final Rect pageRenderPosition;
    private AtlasPage currentPage;
    private byte includedMap;
    public AtlasScreen(AtlasMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        Minecraft minecraft = Minecraft.getInstance();
        width = minecraft.getWindow().getGuiScaledWidth();
        height = minecraft.getWindow().getGuiScaledHeight();
        imageWidth = atlasRect.w();
        imageHeight = atlasRect.h();
        leftPos = (width - imageWidth) / 2;
        topPos = (height - imageHeight) / 2;
        pageRenderPosition = new Rect(leftPos + pageRenderPositionLocal.x(), topPos + pageRenderPositionLocal.y(), 162, 147);
    }
    @Override
    public boolean isPauseScreen() {
        return false;
    }
    @SuppressWarnings("null")
    @Override
    protected void init() {
        int buttonPosY = topPos + 20;
        includedMap = menu.getAtlasStack().getTag().getByte("IncludedMap");
        for (int i = 0; i < 6; ++i) {
            if (((includedMap >>> i) & 1) == 1) {
                addRenderableWidget(PageButton.create(atlasTextureAtlas, leftPos + 180, buttonPosY, AtlasPages.create(i), this::onPageButtonPressed));
                buttonPosY += 20;
            }
        }
        currentPage = addRenderableWidget(PageButton.create(atlasTextureAtlas, leftPos + 180, buttonPosY, new PaperAndInkPage(), this::onPageButtonPressed)).getPage();
    }
    @Override
    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        GuiRenderer.sprite(guiGraphics, atlasTextureAtlas, leftPos, topPos, atlasRect);
        currentPage.render(guiGraphics, pageRenderPosition.fixed());
    }
    @Override
    protected void renderLabels(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY){
        return;
    }
    private void onPageButtonPressed(Button button) {
        AtlasPage pageWillSwitchTo = ((PageButton)button).getPage();
        if (pageWillSwitchTo == currentPage) {
            return;
        }
        boolean showInventoryLastPage = currentPage.shouldShowInventory;
        currentPage = pageWillSwitchTo;
        if (showInventoryLastPage != currentPage.shouldShowInventory) {
            for (Slot slot : menu.slots) {
                ((HideableSlot)slot).setActive(currentPage.shouldShowInventory);
            }
        }
    }
}
