package org.quacdev.limbo.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import org.quacdev.limbo.block.ModBlocks;

public class AbyssalGrassBlock extends Block {
    public AbyssalGrassBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        BlockState plant = plantable.getPlant(world, pos.relative(facing));

        if(plantable instanceof SaplingBlock) {
            return plant.getBlock() == ModBlocks.ABYSSAL_SAPLING.get();
        }
        return false;
    }
}
