package org.quacdev.limbo.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.quacdev.limbo.Limbo;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Limbo.MODID);

    public static final RegistryObject<Item> UNSTABLE_LIMBO_FRAGMENT = ITEMS.register("unstable_limbo_fragment",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STABILIZED_LIMBO_FRAGMENT = ITEMS.register("stabilized_limbo_fragment",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> LIMBO_EYE = ITEMS.register("limbo_eye",
            () -> new Item(new Item.Properties()));
}
