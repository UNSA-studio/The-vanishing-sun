package cn.autoforged.the_vanishing_sun_1777640964.event;

import cn.autoforged.the_vanishing_sun_1777640964.TheVanishingSun;
import cn.autoforged.the_vanishing_sun_1777640964.entity.ModEntities;
import cn.autoforged.the_vanishing_sun_1777640964.entity.client.LifePodModel;
import cn.autoforged.the_vanishing_sun_1777640964.entity.client.LifePodRenderer;
import cn.autoforged.the_vanishing_sun_1777640964.screen.ModMenuTypes;
import cn.autoforged.the_vanishing_sun_1777640964.screen.custom.LifePodScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = TheVanishingSun.MODID, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LifePodModel.LAYER_LOCATION, LifePodModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.LIFE_POD.get(), LifePodRenderer::new);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.LIFE_POD_MENU.get(), LifePodScreen::new);
    }
}
