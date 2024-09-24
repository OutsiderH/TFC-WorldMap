package com.outsiderh.tfcworldmap.client;

import javax.annotation.Nonnull;

import com.outsiderh.tfcworldmap.client.collection.FixedRect;
import com.outsiderh.tfcworldmap.client.collection.Rect;
import com.outsiderh.tfcworldmap.client.page.AtlasPage;
import com.outsiderh.tfcworldmap.client.page.AtlasPages;
import com.outsiderh.tfcworldmap.client.page.PaperAndInkPage;
import com.outsiderh.tfcworldmap.common.Main;
import com.outsiderh.tfcworldmap.common.menu.AtlasMenu;
import com.outsiderh.tfcworldmap.common.menu.slot.IHideable;
import com.outsiderh.tfcworldmap.common.save.AtlasData;

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
    private static final ResourceLocation atlasTextureAtlas = new ResourceLocation(Main.modId, "textures/gui/atlas.png");
    private static final FixedRect atlasRect = new FixedRect(1, 1, 190, 180);
    private static final FixedRect inventoryBarRect = new FixedRect(93, 237, 162, 18);
    private static final FixedRect inventorySlotRect = new FixedRect(93, 237, 18, 18);
    private static final FixedRect incSacIconRect = new FixedRect(193, 1, 16, 16);
    private static final FixedRect paperIconRect = new FixedRect(209, 1, 16, 16);
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
    public void render(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
    @Override
    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        GuiRenderer.sprite(guiGraphics, atlasTextureAtlas, leftPos, topPos, atlasRect);
        if (currentPage.shouldShowInventory) {
            GuiRenderer.sprite(guiGraphics, atlasTextureAtlas, pageRenderPosition.x, pageRenderPosition.y2() - 17, inventoryBarRect);
            GuiRenderer.sprite(guiGraphics, atlasTextureAtlas, pageRenderPosition.x, pageRenderPosition.y2() - 38, inventoryBarRect);
            GuiRenderer.sprite(guiGraphics, atlasTextureAtlas, pageRenderPosition.x, pageRenderPosition.y2() - 56, inventoryBarRect);
            GuiRenderer.sprite(guiGraphics, atlasTextureAtlas, pageRenderPosition.x, pageRenderPosition.y2() - 74, inventoryBarRect);
            GuiRenderer.sprite(guiGraphics, atlasTextureAtlas, leftPos + 77, topPos + 30, inventorySlotRect);
            GuiRenderer.sprite(guiGraphics, atlasTextureAtlas, leftPos + 95, topPos + 30, inventorySlotRect);
            GuiRenderer.sprite(guiGraphics, atlasTextureAtlas, leftPos + 78, topPos + 31, paperIconRect);
            GuiRenderer.sprite(guiGraphics, atlasTextureAtlas, leftPos + 96, topPos + 31, incSacIconRect);
        }
        currentPage.render(guiGraphics, pageRenderPosition.fixed(), AtlasData.of(menu.getExtraData()));
    }
    @Override
    protected void renderLabels(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY){
        if (currentPage.shouldShowInventory) {
            GuiRenderer.text(guiGraphics, playerInventoryTitle, pageRenderPositionLocal.x(), pageRenderPositionLocal.y2() - 84, 0xFFFFFFFF);
        }
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
                ((IHideable)slot).setActive(currentPage.shouldShowInventory);
            }
        }
    }
}
