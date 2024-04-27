package org.quacdev.limbo.worldgen.biome.surface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.quacdev.limbo.block.ModBlocks;
import org.quacdev.limbo.worldgen.biome.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource ABYSSAL_GRASS_BLOCK = makeStateRule(ModBlocks.ABYSSAL_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource ABYSSAL_DIRT = makeStateRule(ModBlocks.ABYSSAL_DIRT.get());
    private static final SurfaceRules.RuleSource BLOCK_D = makeStateRule(Blocks.ORANGE_WOOL);
    private static final SurfaceRules.RuleSource BLOCK_E = makeStateRule(Blocks.BLUE_WOOL);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, ABYSSAL_GRASS_BLOCK), ABYSSAL_DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.ABYSSAL_FOREST), SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface), // TOP LAYER
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, ABYSSAL_DIRT), // BELOW TOP LAYER FOR ~2 BLOCKS
                                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, ABYSSAL_DIRT), // LOWER FOR ~3 BLOCKS
                                SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, BLOCK_D) // EVEN LOWER FOR ~12 BLOCKS
                        )),

                        SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.ABYSSAL_FOREST), BLOCK_E)) //ALL POSSIBLE REMAINING STONE
        );
    }


    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
