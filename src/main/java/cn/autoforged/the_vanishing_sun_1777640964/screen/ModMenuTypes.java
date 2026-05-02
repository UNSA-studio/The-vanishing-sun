package cn.autoforged.the_vanishing_sun_1777640964.screen;

import cn.autoforged.the_vanishing_sun_1777640964.TheVanishingSun;
import cn.autoforged.the_vanishing_sun_1777640964.screen.custom.LifePodMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, TheVanishingSun.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<LifePodMenu>> LIFE_POD_MENU =
            MENUS.register("life_pod_menu",
                    () -> IMenuTypeExtension.create(LifePodMenu::new));
}
