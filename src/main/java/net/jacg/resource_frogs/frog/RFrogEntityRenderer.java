package net.jacg.resource_frogs.frog;

import net.jacg.resource_frogs.ResourceFrogs;
import net.jacg.resource_frogs.ResourceFrogsClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class RFrogEntityRenderer extends MobEntityRenderer<RFrogEntity, RFrogEntityModel<RFrogEntity>> {
    public RFrogEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new RFrogEntityModel(context.getPart(ResourceFrogsClient.MODEL_FROG_LAYER)), 0.3f);
        this.addFeature(new RFrogFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(RFrogEntity entity) {
        // temporary
        return ResourceFrogs.id(entity.getType().getUntranslatedName());
    }
}
