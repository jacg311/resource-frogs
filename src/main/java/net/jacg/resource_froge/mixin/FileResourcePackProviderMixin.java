package net.jacg.resource_froge.mixin;

import java.nio.file.Path;
import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.loader.api.FabricLoader;
import net.jacg.resource_froge.ResourceFroge;
import net.jacg.resource_froge.util.RFDirectoryResourcePack;
import net.minecraft.resource.FileResourcePackProvider;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;

@Mixin(FileResourcePackProvider.class)
public class FileResourcePackProviderMixin {
    @Inject(method = "register", at = @At("HEAD"))
    private void resource_frogs$register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory, CallbackInfo ci) {
        Path texturePath = FabricLoader.getInstance()
                .getConfigDir()
                .resolve(ResourceFroge.MOD_ID);
        if (texturePath.toFile().exists()) {
            profileAdder.accept(ResourcePackProfile.of(
                    ResourceFroge.MOD_ID,
                    true,
                    () -> new RFDirectoryResourcePack(texturePath, null),
                    factory,
                    ResourcePackProfile.InsertionPosition.TOP,
                    ResourcePackSource.PACK_SOURCE_BUILTIN));
        }
    }
}
