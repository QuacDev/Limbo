package org.quacdev.limbo.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.quacdev.limbo.Limbo;
import org.quacdev.limbo.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Limbo.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //blockWithItem(ModBlocks.LIMBO_BLOCK); REPLACED BY HAND
        blockWithItem(ModBlocks.END_ABYSSAL_CLUSTER);

        logBlock(((RotatedPillarBlock) ModBlocks.ABYSSAL_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.ABYSSAL_WOOD.get()), blockTexture(ModBlocks.ABYSSAL_LOG.get()), blockTexture(ModBlocks.ABYSSAL_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_ABYSSAL_LOG.get()), blockTexture(ModBlocks.STRIPPED_ABYSSAL_LOG.get()),
                new ResourceLocation(Limbo.MODID, "block/stripped_abyssal_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_ABYSSAL_WOOD.get()), blockTexture(ModBlocks.STRIPPED_ABYSSAL_LOG.get()), blockTexture(ModBlocks.STRIPPED_ABYSSAL_LOG.get()));

        blockItem(ModBlocks.ABYSSAL_LOG);
        blockItem(ModBlocks.STRIPPED_ABYSSAL_LOG);
        blockItem(ModBlocks.ABYSSAL_WOOD);
        blockItem(ModBlocks.STRIPPED_ABYSSAL_WOOD);
        blockWithItem(ModBlocks.ABYSSAL_PLANKS);
        leavesBlock(ModBlocks.ABYSSAL_LEAVES);
        saplingBlock(ModBlocks.ABYSSAL_SAPLING);

        topBottomBlock(ModBlocks.ABYSSAL_GRASS_BLOCK, "abyssal_grass_block", "abyssal_dirt", "abyssal_grass_block_top");
        blockItem(ModBlocks.ABYSSAL_GRASS_BLOCK);
        blockWithItem(ModBlocks.ABYSSAL_DIRT);
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(Limbo.MODID + ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"), "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void topBottomBlock(RegistryObject<Block> blockRegistryObject, String side, String bottom, String top) {
        simpleBlock(blockRegistryObject.get(),
                models().cubeBottomTop(
                        ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        new ResourceLocation(Limbo.MODID, "block/" + side),
                        new ResourceLocation(Limbo.MODID, "block/" + bottom),
                        new ResourceLocation(Limbo.MODID, "block/" + top)));
    }
}
