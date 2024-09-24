package com.outsiderh.tfcworldmap.common.save;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class AtlasData implements ICapabilitySerializable<CompoundTag> {
    private String testSave;
    public static AtlasData of(CompoundTag nbt) {
        AtlasData result = new AtlasData();
        result.deserializeNBT(nbt);
        return result;
    }
    public void set(String value) {
        testSave = value;
    }
    public String get() {
        return testSave;
    }
    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
        return capability == AtlasDataCapability.dummy ? LazyOptional.of(() -> this).cast() : LazyOptional.empty();
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag result = new CompoundTag();
        result.putString("testSave", testSave);
        return result;
    }
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        testSave = nbt.getString("testSave");
    }
}
