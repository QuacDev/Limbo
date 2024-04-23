package org.quacdev.limbo.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.quacdev.limbo.Limbo;
import org.quacdev.limbo.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Limbo.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //this.tag(ModTags.Blocks.###)
                //.add(ModBlocks.###.get()).addTag(Tags.Blocks.###);

        //this.tag(BlockTags.MINEABLE_WITH_###)
                //.add(ModBlocks.###.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.END_ABYSSAL_CLUSTER.get());

        //this.tag(BlockTags.NEEDS_###_TOOL)
                //.add(ModBlocks.###.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.END_ABYSSAL_CLUSTER.get());
    }
}
