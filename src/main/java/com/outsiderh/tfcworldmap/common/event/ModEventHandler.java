package com.outsiderh.tfcworldmap.common.event;

import com.outsiderh.tfcworldmap.common.Main;
import com.outsiderh.tfcworldmap.common.save.AtlasDataCapability;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD, modid = Main.modId)
public class ModEventHandler {
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(AtlasDataCapability.class);
    }
}
