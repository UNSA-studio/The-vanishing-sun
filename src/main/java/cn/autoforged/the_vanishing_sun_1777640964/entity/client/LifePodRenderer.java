package cn.autoforged.the_vanishing_sun_1777640964.entity.client;

import cn.autoforged.the_vanishing_sun_1777640964.TheVanishingSun;
import cn.autoforged.the_vanishing_sun_1777640964.entity.custom.LifePodEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class LifePodRenderer extends EntityRenderer<LifePodEntity> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TheVanishingSun.MODID, "textures/entity/life_pod.png");
    private final LifePodModel model;

    public LifePodRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new LifePodModel(context.bakeLayer(LifePodModel.LAYER_LOCATION));
        this.shadowRadius = 0.5f;
    }

    @Override
    public void render(LifePodEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0, 0.8, 0.0);
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entitySolid(this.getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(LifePodEntity entity) {
        return TEXTURE;
    }
}
