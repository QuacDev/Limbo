package org.quacdev.limbo;

import net.minecraft.core.BlockPos;
import org.joml.Vector3d;

public class LimboUtils {
    public static double BlockPosDistance(BlockPos pos, BlockPos center) {
        return Vector3d.distance(pos.getX(), pos.getY(), pos.getZ(), center.getX(), center.getY(), center.getZ());
    }
}
