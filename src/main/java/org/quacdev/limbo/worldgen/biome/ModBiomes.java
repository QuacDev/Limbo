package org.quacdev.limbo.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import org.quacdev.limbo.Limbo;

public class ModBiomes {
    public static final ResourceKey<Biome> ABYSSAL_FOREST = register("abyssal_forest");

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(ABYSSAL_FOREST, abyssalForest(context));
    }

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Limbo.MODID, name));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static void overworldNoCaveGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
    }

    private static Biome abyssalForest(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 5, 4, 4));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));

        // Follow same order as vanilla biomes for the BiomeDefaultFeatures
        //globalOverworldGeneration(biomeBuilder);

        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_FLOWER_FOREST);

        //BiomeDefaultFeatures.addDefaultSeagrass(biomeBuilder);
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures().###);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true) // Rain
                .downfall(99f).temperature(50f) // ???
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x1e1321)
                        .waterFogColor(0x070214)
                        .skyColor(0x1e1124)
                        .grassColorOverride(0x260e3e)
                        .foliageColorOverride(0x464347)
                        .fogColor(0x2b242e)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        //.backgroundMusic(Musics.createGameMusic(ModSounds.###.getHolder().get()))
                        .build())
                .build();
    }
}
