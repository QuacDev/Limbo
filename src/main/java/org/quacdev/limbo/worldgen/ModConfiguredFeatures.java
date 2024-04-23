package org.quacdev.limbo.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.quacdev.limbo.Limbo;
import org.quacdev.limbo.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_ABYSSAL_CLUSTER_KEY = registerKey("end_abyssal_cluster");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        RuleTest endstoneReplaceable = new BlockMatchTest(Blocks.END_STONE);

        /*
        List<OreConfiguration.TargetBlockState> overworld###Ores = List.of(OreConfiguration.target(stoneReplaceable,
                ModBlocks.###.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.###.get().defaultBlockState()));
        */

        register(context, END_ABYSSAL_CLUSTER_KEY, Feature.ORE, new OreConfiguration(
                endstoneReplaceable,
                ModBlocks.END_ABYSSAL_CLUSTER.get().defaultBlockState(),
                3)); // VEIN SIZE
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Limbo.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
