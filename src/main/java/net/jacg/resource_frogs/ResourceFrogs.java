package net.jacg.resource_frogs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.jacg.resource_frogs.frog.RFrogEntity;
import net.jacg.resource_frogs.item.FrogNet;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
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
    public static final FabricLoader LOADER = FabricLoader.getInstance();

    public static List<EntityType<RFrogEntity>> FROG_LIST = new ArrayList<>();

    public static final Item FROG_NET = Registry.register(Registry.ITEM, id("frog_net"), new FrogNet(new Item.Settings().maxCount(1)));
    @Override
    public void onInitialize() {
        File[] files = FabricLoader.getInstance().getConfigDir().resolve("resource_frogs/frogs").toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                try {

                    registerFrog(FilenameUtils.removeExtension(file.getName().toLowerCase(Locale.ENGLISH)));
                } catch (Exception e) {
                    LOGGER.warn("Exception when registering frogs. " + e.getMessage());
                }
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
                        .build());

        FabricDefaultAttributeRegistry.register(frog, FrogEntity.createFrogAttributes());
        registerSpawnEgg(name + "spawn_egg", frog, 12895428, 11382189);
        FROG_LIST.add(frog);
    }

    public static void registerSpawnEgg(String name, EntityType<RFrogEntity> type, int primaryColor, int secondaryColor) {
        Registry.register(Registry.ITEM, id(name), new SpawnEggItem(type, primaryColor, secondaryColor, new Item.Settings().group(ItemGroup.MISC)));
    }
}
