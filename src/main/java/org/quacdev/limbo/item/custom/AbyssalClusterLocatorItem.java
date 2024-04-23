package org.quacdev.limbo.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.quacdev.limbo.block.ModBlocks;

public class AbyssalClusterLocatorItem extends Item {
    public AbyssalClusterLocatorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(!context.getLevel().isClientSide) {
            BlockPos posClicked = context.getClickedPos();
            ChunkPos chunkClicked = context.getLevel().getChunkAt(posClicked).getPos();
            BlockPos startBlockPos = chunkClicked.getWorldPosition().atY(100);

            boolean found = false;

            for (int y = 0; y < 100; y++) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        BlockPos a = startBlockPos.below(y).east(x).south(z);
                        if (context.getLevel().getBlockState(a).getBlock() == ModBlocks.END_ABYSSAL_CLUSTER.get()) {
                            sendPlayerCoords(a, context.getPlayer());
                            found = true;
                        }
                    }
                }
            }

            if(!found) {
                context.getPlayer().sendSystemMessage(Component.literal("Couldn't find any End Abyssal Clusters"));
            }
        }

        return InteractionResult.SUCCESS;
    }

    private void sendPlayerCoords(BlockPos pos, Player player) {
        player.sendSystemMessage(
                Component.literal("Found Abyssal Cluster At §a[" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "]§e Click To Teleport 2 Blocks Above")
                        .setStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + pos.getX() + " " + (pos.getY()+2) + " " + pos.getZ()))));
    }
}
