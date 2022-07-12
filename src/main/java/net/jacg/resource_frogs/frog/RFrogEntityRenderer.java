package net.jacg.resource_frogs.frog;

import net.jacg.resource_frogs.ResourceFrogsClient;
import net.jacg.resource_frogs.util.Util;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.FrogEntityModel;
import net.minecraft.util.Identifier;

public class RFrogEntityRenderer extends MobEntityRenderer<RFrogEntity, FrogEntityModel<RFrogEntity>> {
    private Identifier texture;
    public RFrogEntityRenderer(EntityRendererFactory.Context context, String texture, boolean glows) {
        super(context, new FrogEntityModel<>(context.getPart(ResourceFrogsClient.MODEL_FROG_LAYER)), 0.3f);
        this.texture = Util.id("textures/" + texture + ".png");
        if (glows) {
            this.addFeature(new RFrogFeatureRenderer(this, texture));
        }
    }

    @Override
    public Identifier getTexture(RFrogEntity entity) {
        return texture;
    }
}
