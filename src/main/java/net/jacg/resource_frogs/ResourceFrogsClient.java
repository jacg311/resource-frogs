package net.jacg.resource_frogs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.jacg.resource_frogs.frog.RFrogEntity;
import net.jacg.resource_frogs.frog.RFrogEntityModel;
import net.jacg.resource_frogs.frog.RFrogEntityRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.EntityType;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;

@Environment(EnvType.CLIENT)
public class ResourceFrogsClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_FROG_LAYER = new EntityModelLayer(ResourceFrogs.id("frog"), "main");

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(MODEL_FROG_LAYER, RFrogEntityModel::getTexturedModelData);

        for (EntityType<RFrogEntity> frogEntity : ResourceFrogs.FROG_LIST) {
            EntityRendererRegistry.register(frogEntity, RFrogEntityRenderer::new);
        }
    }
}
