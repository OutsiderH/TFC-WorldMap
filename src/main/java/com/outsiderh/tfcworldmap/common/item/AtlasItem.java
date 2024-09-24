package com.outsiderh.tfcworldmap.common.item;

import javax.annotation.Nonnull;

import com.outsiderh.tfcworldmap.common.AtlasItemStackHandler;
import com.outsiderh.tfcworldmap.common.menu.AtlasMenu;
import com.outsiderh.tfcworldmap.common.menu.provider.ItemStackMenuProvider;
import com.outsiderh.tfcworldmap.common.save.AtlasDataCapability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class AtlasItem extends Item {
    public static final String includedMapKey = "IncludedMap";
    public static final String containedItem0Key = "ContainedItem0";
    public static final String containedItem1Key = "ContainedItem1";
    public static final String savedFileIdKey = "SavedFileId";
    public static AtlasItemStackHandler getItems(ItemStack atlasStack) {
        return new AtlasItemStackHandler(atlasStack);
    }
    public AtlasItem() {
        super(new Item.Properties()
            .rarity(Rarity.UNCOMMON)
            .stacksTo(1));
    }
    @Override
    public InteractionResultHolder<ItemStack> use(@Nonnull Level level, @Nonnull Player player, @Nonnull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        if (player.isShiftKeyDown()) {
            return InteractionResultHolder.pass(itemStack);
        }
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            CompoundTag tag = itemStack.getOrCreateTag();
            if (!tag.contains(includedMapKey)) {
                tag.putByte(includedMapKey, (byte)0b000001);
            }
            if (!tag.contains(containedItem0Key)) {
                tag.put(containedItem0Key, ItemStack.EMPTY.save(new CompoundTag()));
            }
            if (!tag.contains(containedItem1Key)) {
                tag.put(containedItem1Key, ItemStack.EMPTY.save(new CompoundTag()));
            }
            serverPlayer.inventoryMenu.sendAllDataToRemote();
            new ItemStackMenuProvider(AtlasMenu::create, null).openScreen(serverPlayer, usedHand, buffer -> buffer.writeNbt(serverPlayer.getCapability(AtlasDataCapability.dummy).resolve().get().serializeNBT()));
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
    }
}
