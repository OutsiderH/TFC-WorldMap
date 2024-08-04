package com.outsiderh.tfcworldmap.common.item;

import javax.annotation.Nonnull;

import com.outsiderh.tfcworldmap.common.menu.AtlasMenu;
import com.outsiderh.tfcworldmap.common.menu.provider.ItemStackMenuProvider;

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
            if (!tag.contains("IncludedMap")) {
                tag.putByte("IncludedMap", (byte)0b000001);
            }
            serverPlayer.inventoryMenu.sendAllDataToRemote();
            new ItemStackMenuProvider(AtlasMenu::create, null).openScreen(serverPlayer, usedHand);
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
    }
}
