package net.jacg.resource_frogs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.jacg.resource_frogs.frog.RFrogEntity;
import net.jacg.resource_frogs.frog.RFrogEntityModel;
import net.jacg.resource_frogs.frog.RFrogEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;

import java.io.File;
import java.util.Locale;

@Environment(EnvType.CLIENT)
public class ResourceFrogsClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_FROG_LAYER = new EntityModelLayer(ResourceFrogs.id("frog"), "main");

    @Override
    public void onInitializeClient() {
        for (EntityType<RFrogEntity> frogEntity : ResourceFrogs.FROG_LIST) {
            EntityRendererRegistry.register(frogEntity, RFrogEntityRenderer::new);
        }
        EntityModelLayerRegistry.registerModelLayer(MODEL_FROG_LAYER, RFrogEntityModel::getTexturedModelData);
        File[] textures = new File(FabricLoader.getInstance().getConfigDir().toFile(), "resource_frogs/textures").listFiles(
                (file, s) -> s.toLowerCase(Locale.ENGLISH).endsWith(".png"));

        for (File file : textures) {
            System.out.println(file.getName());
        }
    }
}
