package com.outsiderh.tfcworldmap.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.outsiderh.tfcworldmap.common.item.AtlasItem;
import com.outsiderh.tfcworldmap.common.item.MapItem;
import com.outsiderh.tfcworldmap.common.menu.AtlasMenu;
import com.outsiderh.tfcworldmap.common.util.registry.RegistryUtil;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(Main.modId)
public final class Main {
    public static final String modId = "tfcworldmap";
    public static final Logger logger = LogManager.getLogger(modId);
    private static final DeferredRegister<Item> itemRegister = DeferredRegister.create(ForgeRegistries.ITEMS, modId);
    private static final DeferredRegister<CreativeModeTab> creativeModTabRegister = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, modId);
    private static final DeferredRegister<MenuType<?>> menuRegister = DeferredRegister.create(ForgeRegistries.MENU_TYPES, modId);
    public Main() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        itemRegister.register(modEventBus);
        creativeModTabRegister.register(modEventBus);
        menuRegister.register(modEventBus);
        RegistryObjects.mapItem = itemRegister.register("map", MapItem::new);
        RegistryObjects.atlasItem = itemRegister.register("atlas", AtlasItem::new);
        RegistryObjects.mainCreativeModTab = creativeModTabRegister.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable(String.format("creative_mod_tab.%s.main", Main.modId)))
            .icon(() -> new ItemStack(RegistryObjects.atlasItem.get()))
            .displayItems((param, output) -> {
                output.accept(RegistryObjects.mapItem.get());
                output.accept(RegistryObjects.atlasItem.get());
            })
            .build());
        RegistryObjects.atlasMenu = RegistryUtil.registerItemStackMenu(menuRegister, "atlas", AtlasMenu::create);
    }
    public static class RegistryObjects {
        public static RegistryObject<Item> mapItem;
        public static RegistryObject<Item> atlasItem;
        public static RegistryObject<CreativeModeTab> mainCreativeModTab;
        public static RegistryObject<MenuType<AtlasMenu>> atlasMenu;
    }
}