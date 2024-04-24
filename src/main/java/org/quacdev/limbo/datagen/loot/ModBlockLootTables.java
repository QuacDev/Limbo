package org.quacdev.limbo.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.quacdev.limbo.block.ModBlocks;
import org.quacdev.limbo.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //this.dropSelf(ModBlocks.###.get());

        //this.add(ModBlocks.###_ORE.get(),
                //block -> createFortuneOreDrops(ModBlocks.###_ORE.get(), ModItems.###.get(), #f, #f));

        this.add(ModBlocks.END_ABYSSAL_CLUSTER.get(),
                block -> createOreDrops(ModBlocks.END_ABYSSAL_CLUSTER.get(), ModItems.LIMBO_EYE.get(), 1, 3));


        this.dropSelf(ModBlocks.ABYSSAL_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_ABYSSAL_LOG.get());
        this.dropSelf(ModBlocks.ABYSSAL_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_ABYSSAL_WOOD.get());
        this.dropSelf(ModBlocks.ABYSSAL_PLANKS.get());
        this.add(ModBlocks.ABYSSAL_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.LIMBO_BLOCK.get(), NORMAL_LEAVES_SAPLING_CHANCES));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected LootTable.Builder createOreDrops(Block block, Item item, float min, float max) {
        return createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))));
    }

    protected LootTable.Builder createFortuneOreDrops(Block block, Item item, float min, float max) {
        return createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
}
