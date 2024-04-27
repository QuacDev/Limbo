package org.quacdev.limbo.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AcaciaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.quacdev.limbo.Limbo;
import org.quacdev.limbo.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_ABYSSAL_CLUSTER_KEY = registerKey("end_abyssal_cluster");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ABYSSAL_TREE_KEY = registerKey("abyssal_tree");

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

        /*register(context, ABYSSAL_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.ABYSSAL_LOG.get()),
                new CherryTrunkPlacer(
                        5,4,3,
                        ConstantInt.of(3), ConstantInt.of(3),
                        UniformInt.of(1, 3),
                        ConstantInt.of(3)
                ),
                BlockStateProvider.simple(ModBlocks.ABYSSAL_LEAVES.get()),
                new CherryFoliagePlacer(
                        ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(2),
                        2f,2f,2f,2f
                ),
                new TwoLayersFeatureSize(1, 0, 2)).build());*/
        register(context, ABYSSAL_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.ABYSSAL_LOG.get()),
                new FancyTrunkPlacer(5,3,4),
                BlockStateProvider.simple(ModBlocks.ABYSSAL_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 2),
                new TwoLayersFeatureSize(1, 0, 2)).build());
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
