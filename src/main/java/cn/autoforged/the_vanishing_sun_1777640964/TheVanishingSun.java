package cn.autoforged.the_vanishing_sun_1777640964;

import cn.autoforged.the_vanishing_sun_1777640964.block.ModBlocks;
import cn.autoforged.the_vanishing_sun_1777640964.datagen.DataGenerators;
import cn.autoforged.the_vanishing_sun_1777640964.entity.ModEntities;
import cn.autoforged.the_vanishing_sun_1777640964.item.ModItems;
import cn.autoforged.the_vanishing_sun_1777640964.screen.ModMenuTypes;
import cn.autoforged.the_vanishing_sun_1777640964.ui.ModTabs;
import cn.autoforged.the_vanishing_sun_1777640964.worldgen.ModFeatures;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(TheVanishingSun.MODID)
public class TheVanishingSun {
    public static final String MODID = "the_vanishing_sun_1777640964";

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
