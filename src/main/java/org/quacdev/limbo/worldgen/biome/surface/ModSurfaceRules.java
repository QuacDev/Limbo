package org.quacdev.limbo.worldgen.biome.surface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.quacdev.limbo.block.ModBlocks;
import org.quacdev.limbo.worldgen.biome.ModBiomes;

public class ModSurfaceRules {
    /*private static final SurfaceRules.RuleSource LIMBO_BLOCK = makeStateRule(ModBlocks.LIMBO_BLOCK.get());
    private static final SurfaceRules.RuleSource SCULK = makeStateRule(Blocks.SCULK);
    private static final SurfaceRules.RuleSource OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);*/

    private static final SurfaceRules.RuleSource BLOCK_A = makeStateRule(Blocks.BLACK_WOOL);
    private static final SurfaceRules.RuleSource BLOCK_B = makeStateRule(Blocks.RED_WOOL);
    private static final SurfaceRules.RuleSource BLOCK_C = makeStateRule(Blocks.WHITE_WOOL);
    private static final SurfaceRules.RuleSource BLOCK_D = makeStateRule(Blocks.ORANGE_WOOL);
    private static final SurfaceRules.RuleSource BLOCK_E = makeStateRule(Blocks.BLUE_WOOL);

    public static SurfaceRules.RuleSource makeRules() {
        return SurfaceRules.sequence(
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.LIMBO_FOREST), SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BLOCK_A),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, BLOCK_B),
                                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, BLOCK_C),
                                SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, BLOCK_D)
                        )),

                        SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.LIMBO_FOREST), BLOCK_E))
        );
    }


    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}