package org.quacdev.limbo.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import org.quacdev.limbo.Limbo;

public class ModTags {
    public static void init() {
        Biomes.init();
    }

    public static class Biomes {
        private static void init() {}

        public static final TagKey<Biome> IS_ABYSSAL_FOREST = tag("is_abyssal_forest");

        private static TagKey<Biome> tag(String name)
        {
            return TagKey.create(Registries.BIOME, new ResourceLocation(Limbo.MODID, name));
        }
    }
}
