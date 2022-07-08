package net.jacg.resource_frogs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.jacg.resource_frogs.config.FrogConfig;
import net.jacg.resource_frogs.frog.RFrogEntity;
import net.jacg.resource_frogs.frog.RFrogEntityRenderer;
import net.jacg.resource_frogs.gui.FrogPediaScreen;
import net.jacg.resource_frogs.util.Util;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.FrogEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Pair;

@Environment(EnvType.CLIENT)
public class ResourceFrogsClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_FROG_LAYER = new EntityModelLayer(Util.id("frog"), "main");

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ResourceFrogs.FROGPEDIA_SCREENHANDLER, FrogPediaScreen::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_FROG_LAYER, FrogEntityModel::getTexturedModelData);

        for (Pair<EntityType<RFrogEntity>, FrogConfig> pair : ResourceFrogs.FROG_LIST) {
            EntityRendererRegistry.register(pair.getLeft(), ctx -> new RFrogEntityRenderer(ctx, pair));
        }
    }
}
