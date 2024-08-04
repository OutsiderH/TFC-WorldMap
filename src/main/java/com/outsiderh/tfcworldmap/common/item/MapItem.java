package com.outsiderh.tfcworldmap.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class MapItem extends Item {
    public MapItem() {
        super(new Item.Properties()
            .rarity(Rarity.COMMON)
            .stacksTo(1));
    }
}
