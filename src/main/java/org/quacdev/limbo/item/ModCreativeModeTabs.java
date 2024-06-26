package org.quacdev.limbo.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.quacdev.limbo.Limbo;
import org.quacdev.limbo.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Limbo.MODID);

    public static final RegistryObject<CreativeModeTab> LIMBO_TAB = CREATIVE_MODE_TABS.register("limbo_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.LIMBO_BLOCK.get()))
                    .title(Component.translatable("creativetab.limbo_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.UNSTABLE_LIMBO_FRAGMENT.get());
                        output.accept(ModItems.STABILIZED_LIMBO_FRAGMENT.get());
                        output.accept(ModItems.LIMBO_EYE.get());
                        output.accept(ModItems.ABYSSAL_CLUSTER_LOCATOR.get());

                        output.accept(ModBlocks.LIMBO_BLOCK.get());

                        // ABYSSAL
                        output.accept(ModBlocks.ABYSSAL_GRASS_BLOCK.get());
                        output.accept(ModBlocks.ABYSSAL_DIRT.get());

                        output.accept(ModBlocks.END_ABYSSAL_CLUSTER.get());

                        output.accept(ModBlocks.ABYSSAL_LOG.get());
                        output.accept(ModBlocks.STRIPPED_ABYSSAL_LOG.get());
                        output.accept(ModBlocks.ABYSSAL_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_ABYSSAL_WOOD.get());
                        output.accept(ModBlocks.ABYSSAL_PLANKS.get());
                        output.accept(ModBlocks.ABYSSAL_LEAVES.get());
                        output.accept(ModBlocks.ABYSSAL_SAPLING.get());
                    })
                    .build());
}
