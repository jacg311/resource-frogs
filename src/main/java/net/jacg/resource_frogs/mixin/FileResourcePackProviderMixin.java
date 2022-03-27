package net.jacg.resource_frogs.mixin;

import net.jacg.resource_frogs.ResourceFrogs;
import net.jacg.resource_frogs.util.RFDirectoryResourcePack;
import net.minecraft.resource.FileResourcePackProvider;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.function.Consumer;

@Mixin(FileResourcePackProvider.class)
public class FileResourcePackProviderMixin {
    @Inject(method = "register", at = @At("HEAD"))
    private void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory, CallbackInfo ci) {
        File texturePath = ResourceFrogs.LOADER.getConfigDir().resolve("resource_frogs/textures").toFile();
        if (texturePath.exists() && texturePath.canRead()) {
            profileAdder.accept(ResourcePackProfile.of(
                    ResourceFrogs.MOD_ID,
                    true,
                    () -> new RFDirectoryResourcePack(texturePath),
                    factory,
                    ResourcePackProfile.InsertionPosition.TOP,
                    ResourcePackSource.PACK_SOURCE_BUILTIN));
        }
    }
}
