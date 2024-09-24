package com.outsiderh.tfcworldmap.common.util.registry;

import com.outsiderh.tfcworldmap.common.menu.ItemStackMenu;
import com.outsiderh.tfcworldmap.common.menu.ItemStackMenu.Factory;
import com.outsiderh.tfcworldmap.common.menu.provider.ItemStackMenuProvider;
import com.outsiderh.tfcworldmap.common.menu.provider.ItemStackMenuProvider.BufferInfo;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public abstract class RegistryUtil {
    public static <T extends ItemStackMenu> RegistryObject<MenuType<T>> registerItemStackMenu(DeferredRegister<MenuType<?>> menuRegister, String name, Factory<T> factory) {
        return registerMenu(menuRegister, name, (id, inventory, buffer) -> {
            final BufferInfo info = ItemStackMenuProvider.readBuffer(buffer, inventory);
            return factory.create(id, inventory, info.usedHand(), info.usedSlot(), info.usedStack(), info.extraData());
        });
    }
    public static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenu(DeferredRegister<MenuType<?>> menuRegister, String name, IContainerFactory<T> factory) {
        return menuRegister.register(name, () -> IForgeMenuType.create(factory));
    }
}
