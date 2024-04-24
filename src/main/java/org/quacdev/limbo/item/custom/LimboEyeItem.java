package org.quacdev.limbo.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.quacdev.limbo.LimboUtils;
import org.quacdev.limbo.block.ModBlocks;
import org.quacdev.limbo.block.custom.LimboBlock;

import java.util.HashSet;
import java.util.Set;

public class LimboEyeItem extends Item {
    public LimboEyeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide()) {
            BlockPos center = player.getOnPos().above(2);
            int radius = 40;
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

            for(int x = -radius; x <= radius; x++) {
                for(int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <=radius; z++) {
                        mutableBlockPos.set(center.offset(x,y,z));
                        if(Math.sqrt(mutableBlockPos.distSqr(center)) <= radius) {
                            BlockState blockState = level.getBlockState(mutableBlockPos);
                            //level.setBlockAndUpdate(pos, Blocks.RED_WOOL.defaultBlockState());
                            if(blockState.getBlock() == ModBlocks.LIMBO_BLOCK.get()) {
                                LimboBlock.ChargeWithFalloff(blockState, player.level(), mutableBlockPos, 9, 1, 2, 5, center);
                            }
                        }
                    }
                }
            }

            player.getCooldowns().addCooldown(this, 100);
        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), true);
    }
}
