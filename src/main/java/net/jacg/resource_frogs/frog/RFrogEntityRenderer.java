package net.jacg.resource_frogs.frog;

import net.jacg.resource_frogs.ResourceFrogs;
import net.jacg.resource_frogs.ResourceFrogsClient;
import net.jacg.resource_frogs.config.FrogConfig;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public class RFrogEntityRenderer extends MobEntityRenderer<RFrogEntity, RFrogEntityModel<RFrogEntity>> {
    public RFrogEntityRenderer(EntityRendererFactory.Context context, Pair<EntityType<RFrogEntity>, FrogConfig> pair) {
        super(context, new RFrogEntityModel<>(context.getPart(ResourceFrogsClient.MODEL_FROG_LAYER)), 0.3f);
        if (pair.getRight().hasGlowFeature) {
            this.addFeature(new RFrogFeatureRenderer(this, pair.getLeft().getUntranslatedName()));
        }
    }

    @Override
    public Identifier getTexture(RFrogEntity entity) {
        return ResourceFrogs.id(entity.getType().getUntranslatedName());
    }
}
