package unsa.tvs.com;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import unsa.tvs.com.block.ModBlocks;
import unsa.tvs.com.command.SunTimerCommand;
import unsa.tvs.com.datagen.DataGenerators;
import unsa.tvs.com.entity.ModEntities;
import unsa.tvs.com.item.ModItems;
import unsa.tvs.com.screen.ModMenuTypes;
import unsa.tvs.com.ui.ModTabs;
import unsa.tvs.com.world.SunExtinctionHandler;
import unsa.tvs.com.worldgen.ModFeatures;

@Mod(TheVanishingSun.MODID)
public class TheVanishingSun {
    public static final String MODID = "the_vanishing_sun";

    public TheVanishingSun(IEventBus modEventBus) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTabs.CREATIVE_TABS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModMenuTypes.MENUS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);

        modEventBus.addListener(DataGenerators::gatherData);
        
        // 注册事件
        NeoForge.EVENT_BUS.register(SunExtinctionHandler.class);
        modEventBus.addListener(TheVanishingSun::onRegisterCommands);
    }
    
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        SunTimerCommand.register(event.getDispatcher());
    }
}
