package org.quacdev.limbo.block;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockStateProperties {
    public static final IntegerProperty CHARGE_LEVEL = IntegerProperty.create("charge_level", 0, 10); // name, min, max
}
