package cn.autoforged.the_vanishing_sun_1777640964.ui;

import cn.autoforged.the_vanishing_sun_1777640964.TheVanishingSun;
import cn.autoforged.the_vanishing_sun_1777640964.block.ModBlocks;
import cn.autoforged.the_vanishing_sun_1777640964.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheVanishingSun.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MOD_TAB =
            CREATIVE_TABS.register("tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + TheVanishingSun.MODID))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(() -> ModItems.BLUEPRINT.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BLUEPRINT.get());
                        output.accept(ModItems.RADAR.get());
                        output.accept(ModItems.LOCATOR.get());
                        output.accept(ModItems.BIOLOGICAL_JAR.get());
                        output.accept(ModBlocks.BLUEPRINT_PARSING_STATION.get());
                        output.accept(ModBlocks.BIOLOGICAL_CHAMBER.get());
                    })
                    .build());
}
