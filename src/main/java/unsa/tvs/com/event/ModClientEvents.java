package unsa.tvs.com.event;

import unsa.tvs.com.TheVanishingSun;
import unsa.tvs.com.entity.ModEntities;
import unsa.tvs.com.entity.client.LifePodModel;
import unsa.tvs.com.entity.client.LifePodRenderer;
import unsa.tvs.com.screen.ModMenuTypes;
import unsa.tvs.com.screen.custom.LifePodScreen;
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
