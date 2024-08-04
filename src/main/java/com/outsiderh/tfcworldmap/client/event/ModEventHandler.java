package com.outsiderh.tfcworldmap.client.event;

import com.outsiderh.tfcworldmap.client.AtlasScreen;
import com.outsiderh.tfcworldmap.common.Main;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.MOD, modid = Main.modId)
public abstract class ModEventHandler {
    @SubscribeEvent
    public static void onModLoad(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Main.RegistryObjects.atlasMenu.get(), AtlasScreen::new);
        });
    }
}
