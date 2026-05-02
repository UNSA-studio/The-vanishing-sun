package cn.autoforged.the_vanishing_sun;

import cn.autoforged.the_vanishing_sun.block.ModBlocks;
import cn.autoforged.the_vanishing_sun.datagen.DataGenerators;
import cn.autoforged.the_vanishing_sun.entity.ModEntities;
import cn.autoforged.the_vanishing_sun.item.ModItems;
import cn.autoforged.the_vanishing_sun.screen.ModMenuTypes;
import cn.autoforged.the_vanishing_sun.ui.ModTabs;
import cn.autoforged.the_vanishing_sun.worldgen.ModFeatures;
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
