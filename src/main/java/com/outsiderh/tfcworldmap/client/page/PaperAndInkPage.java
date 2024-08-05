package com.outsiderh.tfcworldmap.client.page;

import com.outsiderh.tfcworldmap.client.collection.FixedRect;

public class PaperAndInkPage extends AtlasPage {
    private static final FixedRect buttonSpriteRect = new FixedRect(1, 182, 16, 16);
    public PaperAndInkPage() {
        super(true);
    }
    @Override
    public FixedRect getButtonSpriteRect() {
        return buttonSpriteRect;
    }
}
