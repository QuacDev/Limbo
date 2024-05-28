package org.quacdev.limbo.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.quacdev.limbo.Limbo;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Limbo.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


        generator.addProvider(event.includeServer(), new ModRecipeProvider(output));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(output));

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output, existingFileHelper));


        ModBlockTagProvider blockTagProvider = generator.addProvider(event.includeServer(),
                new ModBlockTagProvider(output, lookupProvider, existingFileHelper));
        ModItemTagProvider itemTagProvider = generator.addProvider(event.includeServer(),
                new ModItemTagProvider(output, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper));


        generator.addProvider(event.includeServer(), new ModWorldGenProvider(output, lookupProvider));

        ModBiomeTagProvider biomeTagProvider = generator.addProvider(event.includeServer(),
                new ModBiomeTagProvider(output, lookupProvider, existingFileHelper));
    }
}
