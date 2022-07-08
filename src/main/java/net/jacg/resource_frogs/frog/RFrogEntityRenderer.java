package net.jacg.resource_frogs.frog;

import net.jacg.resource_frogs.ResourceFrogsClient;
import net.jacg.resource_frogs.config.FrogConfig;
import net.jacg.resource_frogs.util.Util;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.FrogEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public class RFrogEntityRenderer extends MobEntityRenderer<RFrogEntity, FrogEntityModel<RFrogEntity>> {
    public RFrogEntityRenderer(EntityRendererFactory.Context context, Pair<EntityType<RFrogEntity>, FrogConfig> pair) {
        super(context, new FrogEntityModel<>(context.getPart(ResourceFrogsClient.MODEL_FROG_LAYER)), 0.3f);
        if (pair.getRight().hasGlowfeature) {
            this.addFeature(new RFrogFeatureRenderer(this, pair.getLeft()
                    .getUntranslatedName()));
        }
    }

    @Override
    public Identifier getTexture(RFrogEntity entity) {
        return Util.id("textures/" + entity.getType()
                .getUntranslatedName() + ".png");
    }
}
