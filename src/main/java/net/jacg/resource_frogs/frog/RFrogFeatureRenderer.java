package net.jacg.resource_frogs.frog;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jacg.resource_frogs.util.Util;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.FrogEntityModel;
import net.minecraft.util.Identifier;

@Environment(value = EnvType.CLIENT)
public class RFrogFeatureRenderer extends EyesFeatureRenderer<RFrogEntity, FrogEntityModel<RFrogEntity>> {
    private final Identifier glowTexture;

    public RFrogFeatureRenderer(
            FeatureRendererContext<RFrogEntity, FrogEntityModel<RFrogEntity>> featureRendererContext, String glowTexture
    ) {
        super(featureRendererContext);
        this.glowTexture = Util.id("textures/" + glowTexture + "_glow.png");
    }

    @Override
    public RenderLayer getEyesTexture() {
        return RenderLayer.getEyes(glowTexture);
    }
}