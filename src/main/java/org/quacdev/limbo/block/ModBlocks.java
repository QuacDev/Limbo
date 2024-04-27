package org.quacdev.limbo.block;

import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.quacdev.limbo.Limbo;
import org.quacdev.limbo.block.custom.*;
import org.quacdev.limbo.item.ModItems;
import org.quacdev.limbo.worldgen.tree.AbyssalTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Limbo.MODID);

    public static final RegistryObject<Block> LIMBO_BLOCK = registerBlock("limbo_block", () -> new LimboBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.SCULK).instrument(NoteBlockInstrument.WITHER_SKELETON)
                    .strength(-1.0F, 3600000.0F).noLootTable().lightLevel((p_60954_) -> 15)));

    //region Abyssal
    public static final RegistryObject<Block> END_ABYSSAL_CLUSTER = registerBlock("end_abyssal_cluster", () -> new Block(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.AMETHYST_CLUSTER).instrument(NoteBlockInstrument.CHIME)
                    .strength(50.0f, 3600000.0f).requiresCorrectToolForDrops())); // Obsidian Strength

    public static final RegistryObject<Block> ABYSSAL_GRASS_BLOCK = registerBlock("abyssal_grass_block", () -> new AbyssalGrassBlock(
            BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).mapColor(MapColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<Block> ABYSSAL_DIRT = registerBlock("abyssal_dirt", () -> new Block(
            BlockBehaviour.Properties.copy(Blocks.DIRT).mapColor(MapColor.COLOR_GRAY)));

    //region Abyssal Wood
    public static final RegistryObject<Block> ABYSSAL_LOG = registerBlock("abyssal_log", () -> new ModNonFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LOG)
                    .mapColor(MapColor.COLOR_PURPLE).strength(15.0f, 250.0f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STRIPPED_ABYSSAL_LOG = registerBlock("stripped_abyssal_log", () -> new ModNonFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_LOG)
                    .mapColor(MapColor.COLOR_PURPLE).strength(15.0f, 250.0f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ABYSSAL_WOOD = registerBlock("abyssal_wood", () -> new ModNonFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.DARK_OAK_WOOD)
                    .mapColor(MapColor.COLOR_PURPLE).strength(15.0f, 250.0f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STRIPPED_ABYSSAL_WOOD = registerBlock("stripped_abyssal_wood", () -> new ModNonFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_WOOD)
                    .mapColor(MapColor.COLOR_PURPLE).strength(15.0f, 250.0f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ABYSSAL_PLANKS = registerBlock("abyssal_planks", () -> new ModPlankBlock(
            BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS)
                    .mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops(), false, 0, 0));
    public static final RegistryObject<Block> ABYSSAL_LEAVES = registerBlock("abyssal_leaves", () -> new ModLeavesBlock(
            BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LEAVES)
                    .mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops(), false, 0, 0));

    public static final RegistryObject<Block> ABYSSAL_SAPLING = registerBlock("abyssal_sapling", () -> new SaplingBlock(new AbyssalTreeGrower(),
            BlockBehaviour.Properties.copy(Blocks.CHERRY_SAPLING)));
    //endregion
    //endregion

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
