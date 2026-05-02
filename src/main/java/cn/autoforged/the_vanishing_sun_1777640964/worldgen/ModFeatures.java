package cn.autoforged.the_vanishing_sun_1777640964.worldgen;

import cn.autoforged.the_vanishing_sun_1777640964.TheVanishingSun;
import cn.autoforged.the_vanishing_sun_1777640964.worldgen.feature.RuinedLabFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, TheVanishingSun.MODID);

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> RUINED_LAB =
            FEATURES.register("ruined_lab", () -> new RuinedLabFeature(NoneFeatureConfiguration.CODEC));
}
