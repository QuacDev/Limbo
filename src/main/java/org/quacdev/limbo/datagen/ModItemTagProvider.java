package org.quacdev.limbo.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.quacdev.limbo.Limbo;
import org.quacdev.limbo.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Limbo.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.LOGS_THAT_BURN).add(
                ModBlocks.ABYSSAL_LOG.get().asItem(),
                ModBlocks.STRIPPED_ABYSSAL_LOG.get().asItem(),
                ModBlocks.ABYSSAL_WOOD.get().asItem(),
                ModBlocks.STRIPPED_ABYSSAL_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS).add(
                ModBlocks.ABYSSAL_PLANKS.get().asItem());
    }
}
