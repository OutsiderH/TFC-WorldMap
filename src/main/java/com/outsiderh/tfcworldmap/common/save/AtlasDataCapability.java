package com.outsiderh.tfcworldmap.common.save;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class AtlasDataCapability<T> {
    public static Capability<AtlasData> dummy = CapabilityManager.get(new CapabilityToken<AtlasData>(){});
}
