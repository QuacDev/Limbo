package org.quacdev.limbo.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.quacdev.limbo.Limbo;
import org.quacdev.limbo.block.ModBlocks;
import org.quacdev.limbo.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Limbo.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.LIMBO_EYE);
        simpleItem(ModItems.ABYSSAL_CLUSTER_LOCATOR);
        simpleItem(ModItems.UNSTABLE_LIMBO_FRAGMENT);
        simpleItem(ModItems.STABILIZED_LIMBO_FRAGMENT);
        blockItem(ModBlocks.ABYSSAL_SAPLING);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Limbo.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder blockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Limbo.MODID, "block/" + item.getId().getPath()));
    }
}
