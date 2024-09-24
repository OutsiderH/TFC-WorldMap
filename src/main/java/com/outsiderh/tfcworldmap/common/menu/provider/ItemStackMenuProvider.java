package com.outsiderh.tfcworldmap.common.menu.provider;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.outsiderh.tfcworldmap.common.menu.ItemStackMenu;
import com.outsiderh.tfcworldmap.common.menu.ItemStackMenu.Factory;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkHooks;

public class ItemStackMenuProvider {
    private final Factory<? extends ItemStackMenu> factory;
    private final @Nullable Component name;
    public static BufferInfo readBuffer(FriendlyByteBuf buffer, Inventory inventory) {
        int usedSlot = buffer.readByte();
        InteractionHand usedHand = usedSlot == -1 ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack usedStack = usedHand == InteractionHand.MAIN_HAND ? inventory.getItem(usedSlot) : inventory.offhand.get(0);
        return new BufferInfo(usedHand, usedSlot, usedStack, buffer.isReadable() ? buffer.readNbt() : null);
    }
    public ItemStackMenuProvider(Factory<? extends ItemStackMenu> factory, @Nullable Component name) {
        this.factory = factory;
        this.name = name;
    }
    public void openScreen(ServerPlayer serverPlayer, InteractionHand usedHand) {
        openScreen(serverPlayer, usedHand, (buffer) -> {});
    }
    public void openScreen(ServerPlayer serverPlayer, InteractionHand usedHand, @Nonnull Consumer<FriendlyByteBuf> bufferConsumer) {
        ItemStack usedStack = serverPlayer.getItemInHand(usedHand);
        int usedSlot = usedHand == InteractionHand.MAIN_HAND ? serverPlayer.getInventory().selected : -1;
        NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
            (id, inventory, player) -> factory.create(id, inventory, usedHand, usedSlot, usedStack, null),
            name == null ? usedStack.getHoverName() : name), (buffer) -> {
                buffer.writeByte(usedSlot);
                bufferConsumer.accept(buffer);
            });
    }
    public record BufferInfo(InteractionHand usedHand, int usedSlot, ItemStack usedStack, @Nullable CompoundTag extraData) {

    };
}
