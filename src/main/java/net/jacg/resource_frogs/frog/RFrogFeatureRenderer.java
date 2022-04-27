package net.jacg.resource_frogs.frog;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jacg.resource_frogs.ResourceFrogs;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

@Environment(value= EnvType.CLIENT)
public class RFrogFeatureRenderer extends EyesFeatureRenderer<RFrogEntity, RFrogEntityModel<RFrogEntity>> {

    public RFrogFeatureRenderer(FeatureRendererContext<RFrogEntity, RFrogEntityModel<RFrogEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return RenderLayer.getEyes(ResourceFrogs.id("redstone_frog_glow"));
    }
}