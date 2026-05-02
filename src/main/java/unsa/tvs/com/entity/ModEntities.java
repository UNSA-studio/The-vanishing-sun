package unsa.tvs.com.entity;

import unsa.tvs.com.TheVanishingSun;
import unsa.tvs.com.entity.custom.LifePodEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, TheVanishingSun.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<LifePodEntity>> LIFE_POD =
            ENTITY_TYPES.register("life_pod",
                    () -> EntityType.Builder.<LifePodEntity>of(LifePodEntity::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .clientTrackingRange(10)
                            .updateInterval(20)
                            .build("life_pod"));
}
