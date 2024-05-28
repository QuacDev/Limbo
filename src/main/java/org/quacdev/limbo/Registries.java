package org.quacdev.limbo;

import net.minecraftforge.eventbus.api.IEventBus;
import org.quacdev.limbo.block.ModBlocks;
import org.quacdev.limbo.item.ModCreativeModeTabs;
import org.quacdev.limbo.item.ModItems;
import org.quacdev.limbo.util.ModTags;
import org.quacdev.limbo.worldgen.biome.ModTerrablender;

public final class Registries {
    public static void Register(IEventBus eventBus) {
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(eventBus);

        ModItems.ITEMS.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
    }
}
