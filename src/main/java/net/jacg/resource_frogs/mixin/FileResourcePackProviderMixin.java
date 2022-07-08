package net.jacg.resource_frogs.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.jacg.resource_frogs.ResourceFrogs;
import net.jacg.resource_frogs.util.RFDirectoryResourcePack;
import net.minecraft.resource.FileResourcePackProvider;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;
import java.util.function.Consumer;

@Mixin(FileResourcePackProvider.class)
public class FileResourcePackProviderMixin {
    @Inject(method = "register", at = @At("HEAD"))
    private void resource_frogs$register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory, CallbackInfo ci) {
        Path texturePath = FabricLoader.getInstance()
                .getConfigDir()
                .resolve(ResourceFrogs.MOD_ID);
        if (texturePath.toFile().exists()) {
            profileAdder.accept(ResourcePackProfile.of(
                    ResourceFrogs.MOD_ID,
                    true,
                    () -> new RFDirectoryResourcePack(texturePath, null),
                    factory,
                    ResourcePackProfile.InsertionPosition.TOP,
                    ResourcePackSource.PACK_SOURCE_BUILTIN));
        }
    }
}
