package org.quacdev.limbo.worldgen.biome;

import net.minecraft.resources.ResourceLocation;
import org.quacdev.limbo.Limbo;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegions(new ResourceLocation(Limbo.MODID, "overworld_1"), 2));
    }
}
