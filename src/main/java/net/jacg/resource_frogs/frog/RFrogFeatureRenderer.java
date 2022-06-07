package net.jacg.resource_frogs.frog;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jacg.resource_frogs.ResourceFrogs;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.FrogEntityModel;

@Environment(value= EnvType.CLIENT)
public class RFrogFeatureRenderer extends EyesFeatureRenderer<RFrogEntity, FrogEntityModel<RFrogEntity>> {
    private final String frogName;

    public RFrogFeatureRenderer(FeatureRendererContext<RFrogEntity, FrogEntityModel<RFrogEntity>> featureRendererContext, String frogName) {
        super(featureRendererContext);
        this.frogName = frogName;
    }

    @Override
    public RenderLayer getEyesTexture() {
        return RenderLayer.getEyes(ResourceFrogs.id("textures/" + frogName + "_glow.png"));
    }
}