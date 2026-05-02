package unsa.tvs.com.worldgen;

import unsa.tvs.com.TheVanishingSun;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> RUINED_LAB =
            createKey("ruined_lab");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(RUINED_LAB, new ConfiguredFeature<>(
                ModFeatures.RUINED_LAB.get(),
                NoneFeatureConfiguration.INSTANCE
        ));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(TheVanishingSun.MODID, name));
    }
}
