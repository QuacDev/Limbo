package org.quacdev.limbo.events;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.quacdev.limbo.Limbo;

@Mod.EventBusSubscriber(modid = Limbo.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onMobSpawn(MobSpawnEvent event) {
        if(event.getEntity().getType() == EntityType.COW) {
            event.setCanceled(true);
        }
    }
}
