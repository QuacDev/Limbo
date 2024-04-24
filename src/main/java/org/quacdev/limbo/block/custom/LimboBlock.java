package org.quacdev.limbo.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.quacdev.limbo.block.ModBlockStateProperties;
import org.quacdev.limbo.block.ModBlocks;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class LimboBlock extends Block {
    public static final IntegerProperty CHARGE_LEVEL;

    public LimboBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CHARGE_LEVEL, 0));
    }

    public static void Charge(BlockState state, Level level, BlockPos blockPos, int i) {
        if(!level.isClientSide) {
            int newValue = state.getValue(CHARGE_LEVEL);
            List<Integer> possibleValues = ModBlockStateProperties.CHARGE_LEVEL.getPossibleValues().stream().toList();
            int maxValue = possibleValues.get(possibleValues.size()-1);
            if(i + newValue > maxValue) {
                newValue = maxValue;
            } else {
                newValue += i;
            }
            //System.out.println("New Charge Value: " + newValue);
            level.setBlockAndUpdate(blockPos, state.setValue(CHARGE_LEVEL, newValue));
            //System.out.println("Recharge at " + blockPos.toShortString() + "!!! New Charge Level: " + level.getBlockState(blockPos).getValue(CHARGE_LEVEL));

            level.scheduleTick(blockPos, ModBlocks.LIMBO_BLOCK.get(), 40);
        }
    }

    public static void ChargeWithFalloff(BlockState state, Level level, BlockPos blockPos, int i, int falloffAmount, int falloffDist, int falloffThresh, BlockPos center) {
        // falloffAmount reduces charge per falloffDist in blocks
        int distance = (int) Math.floor(blockPos.getCenter().distanceTo(center.getCenter()));
        int b = i;
        if(distance > falloffThresh) {
            b -= (falloffAmount * (distance / falloffDist) - (falloffAmount * (falloffThresh / falloffDist)));
        }

        if(b > 0) Charge(state, level, blockPos, b);
    }

    @Override
    public void tick(BlockState state, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if(state.getValue(CHARGE_LEVEL) > 0) {
            serverLevel.setBlockAndUpdate(blockPos, state.setValue(CHARGE_LEVEL, state.getValue(CHARGE_LEVEL) - 1));
            //System.out.println("Charge Level depleted at " + blockPos.toShortString() + "!!! New: " + serverLevel.getBlockState(blockPos).getValue(CHARGE_LEVEL));
            if(serverLevel.getBlockState(blockPos).getValue(CHARGE_LEVEL) > 0) {
                serverLevel.scheduleTick(blockPos, ModBlocks.LIMBO_BLOCK.get(), 20);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(CHARGE_LEVEL);
    }

    static {
        CHARGE_LEVEL = ModBlockStateProperties.CHARGE_LEVEL;
    }
}
