package org.quacdev.limbo.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModLeavesBlock extends LeavesBlock {
    int a,b;
    boolean c;

    public ModLeavesBlock(Properties pProperties, boolean flammable, int flammability, int spreadSpeed) {
        super(pProperties);
        a = flammability;
        b = spreadSpeed;
        c = flammable;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return c;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return a;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return b;
    }
}
