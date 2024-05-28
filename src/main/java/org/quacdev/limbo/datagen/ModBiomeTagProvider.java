package org.quacdev.limbo.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.quacdev.limbo.Limbo;
import org.quacdev.limbo.util.ModTags;
import org.quacdev.limbo.worldgen.biome.ModBiomes;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends BiomeTagsProvider {
    public ModBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Limbo.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // TEMP FIX SINCE IT DOESNT WORK, ORDER WRONG SOMEWHERE?
        /*this.tag(ModTags.Biomes.IS_ABYSSAL_FOREST)
                .add(ModBiomes.ABYSSAL_FOREST);*/
    }
}
