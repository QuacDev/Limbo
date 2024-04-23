package org.quacdev.limbo.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.quacdev.limbo.Limbo;
import org.quacdev.limbo.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Limbo.MODID);

    public static final RegistryObject<Block> LIMBO_BLOCK = registerBlock("limbo_block", () -> new Block(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .sound(SoundType.SCULK).instrument(NoteBlockInstrument.WITHER_SKELETON)
                    .strength(-1.0F, 3600000.0F).noLootTable().lightLevel((p_60954_) -> 15)));

    public static final RegistryObject<Block> END_ABYSSAL_CLUSTER = registerBlock("end_abyssal_cluster", () -> new Block(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.AMETHYST_CLUSTER).instrument(NoteBlockInstrument.CHIME)
                    .strength(50.0f, 3600000.0f).requiresCorrectToolForDrops())); // Obsidian Strength

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
