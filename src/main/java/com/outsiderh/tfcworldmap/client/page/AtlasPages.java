package com.outsiderh.tfcworldmap.client.page;

import javax.annotation.Nullable;

public abstract class AtlasPages {
    @Nullable
    public static AtlasPage create(int index) {
        switch (index) {
            case 0:
                return new CloseViewPage();
            default:
                return null;
        }
    }
}
