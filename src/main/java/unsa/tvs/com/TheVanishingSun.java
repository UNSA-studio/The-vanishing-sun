package unsa.tvs.com;

import unsa.tvs.com.block.ModBlocks;
import unsa.tvs.com.datagen.DataGenerators;
import unsa.tvs.com.entity.ModEntities;
import unsa.tvs.com.item.ModItems;
import unsa.tvs.com.screen.ModMenuTypes;
import unsa.tvs.com.ui.ModTabs;
import unsa.tvs.com.worldgen.ModFeatures;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(TheVanishingSun.MODID)
public class TheVanishingSun {
    public static final String MODID = "the_vanishing_sun";

    public TheVanishingSun(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTabs.CREATIVE_TABS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModMenuTypes.MENUS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);

        modEventBus.addListener(DataGenerators::gatherData);
    }
}
