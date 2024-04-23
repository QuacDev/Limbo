package org.quacdev.limbo.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class ModOverworldRegions extends Region {

    public ModOverworldRegions(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {

        // Easy Replace Way
        /*
        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DARK_FOREST, ModBiomes.LIMBO_FOREST);
        });
        */

        // Complicated Replace Way
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        // Overlap Vanilla's parameters with our own for our LIMBO_FOREST biome.
        // The parameters for this biome are chosen arbitrarily.
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(ParameterUtils.Temperature.span(ParameterUtils.Temperature.WARM, ParameterUtils.Temperature.HOT)) // Type of rain?
                .humidity(ParameterUtils.Humidity.span(ParameterUtils.Humidity.ARID, ParameterUtils.Humidity.DRY)) // Im assuming Rain Stuff
                .continentalness(ParameterUtils.Continentalness.FAR_INLAND) // How far from the Coast it is, ex. FULL_RANGE or COAST or OCEAN
                .erosion(ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1) // Water In Caves //ParameterUtils.Erosion.EROSION_0 // Climate.Parameter.span(0.f, 0.f)
                .depth(ParameterUtils.Depth.FULL_RANGE) // How Far Down It Generates
                .weirdness(ParameterUtils.Weirdness.VALLEY) // Height Difference
                .build().forEach(point -> builder.add(point, ModBiomes.LIMBO_FOREST));

        // Add our points to the mapper
        builder.build().forEach(mapper);
    }
}
