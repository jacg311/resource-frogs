package net.jacg.resource_frogs.frog;

import net.jacg.resource_frogs.ResourceFrogs;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

public class RFrogFeatureRenderer<T extends RFrogEntity, M extends RFrogEntityModel<T>> extends FeatureRenderer<T, M> {
    private static final RenderLayer SKIN = RenderLayer.getEntityCutoutNoCull(ResourceFrogs.id("textures/entity/frog_glow.png"));

    public RFrogFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(SKIN);
        ((Model)this.getContextModel()).render(matrices, vertexConsumer, 0xF00000, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}