package net.jacg.resource_frogs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.jacg.resource_frogs.frog.RFrogEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResourceFrogs implements ModInitializer {
    public static final String MOD_ID = "resource_frogs";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static List<EntityType<RFrogEntity>> FROG_LIST = new ArrayList<>();
    @Override
    public void onInitialize() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("resource_frogs/frogs");

        for (File file : path.toFile().listFiles()) {
            try {
                registerFrog(FilenameUtils.removeExtension(file.getName().toLowerCase(Locale.ENGLISH)));
            } catch (Exception e) {
                LOGGER.warn("Exception when registering frogs. " + e.getMessage());
            }
        }
    }

    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }

    public static void registerFrog(String name) {
        EntityType<RFrogEntity> frog = Registry.register(Registry.ENTITY_TYPE, id(name),
                FabricEntityTypeBuilder
                        .create(SpawnGroup.CREATURE, RFrogEntity::new)
                        .dimensions(new EntityDimensions(0.5f, 0.5f, true))
                        .trackRangeBlocks(10)
                        .build());

        FabricDefaultAttributeRegistry.register(frog, FrogEntity.createFrogAttributes());
        FROG_LIST.add(frog);
    }
}
