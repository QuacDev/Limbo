package org.quacdev.limbo.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModNonFlammableRotatedPillarBlock extends ModFlammableRotatedPillarBlock {

    public ModNonFlammableRotatedPillarBlock(Properties pProperties) {
        super(pProperties, false, 0, 0);
    }
}
