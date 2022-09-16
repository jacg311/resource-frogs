package net.jacg.resource_froge.util;

import static net.jacg.resource_froge.ResourceFroge.FROG_CONFIG_HASH_MAP;
import static net.jacg.resource_froge.ResourceFroge.LOGGER;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;

import blue.endless.jankson.Jankson;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.jacg.resource_froge.ResourceFroge;
import net.jacg.resource_froge.config.FrogConfig;
import net.jacg.resource_froge.frog.RFrogEntity;
import net.jacg.resource_froge.item.RFItemRegistry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Util {
    public final static Jankson JANKSON = new Jankson.Builder()
            .registerDeserializer(String.class, Ingredient.class, (string, m) -> {
                Item item = Registry.ITEM.get(new Identifier(string));
                return Ingredient.ofItems(item);
            })
            /*
            TODO: Find out how to add a second deserializer for this
            .registerDeserializer(String[].class, Ingredient.class, (strings, m) -> {
                System.out.println(Arrays.toString(strings));
                Stream<ItemStack> stream = Arrays.stream(strings).map(stringElem -> {
                    Item item = Registry.ITEM.get(new Identifier(stringElem));
                    return new ItemStack(item);
                });
                return Ingredient.ofStacks(stream);
            })
            */
            .build();

    public static Identifier id(String name) {
        return new Identifier(ResourceFroge.MOD_ID, name);
    }

    public static void registerFrog(String name, FrogConfig config) {
        EntityType<RFrogEntity> frog = Registry.register(Registry.ENTITY_TYPE, id(name), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType<RFrogEntity> entityType, World world) -> new RFrogEntity(entityType, world, config))
                    .dimensions(new EntityDimensions(0.5f, 0.5f, true))
                    .build());

        FabricDefaultAttributeRegistry.register(frog, FrogEntity.createFrogAttributes());
        RFItemRegistry.registerSpawnEgg(name + "_spawn_egg", frog, config.spawnEgg.primaryColor, config.spawnEgg.secondaryColor);
        FROG_CONFIG_HASH_MAP.put(frog, config);
    }

    public static void findFrogsAndRegister() {
        File[] files = FabricLoader.getInstance()
                .getConfigDir()
                .resolve(ResourceFroge.MOD_ID)
                .resolve("frogs")
                .toFile()
                .listFiles();

        if (files == null) {
            LOGGER.warn("No frogs to register found in " + FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve(ResourceFroge.MOD_ID)
                    .resolve("frogs"));
        }
        else {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)) {
                    scanner.useDelimiter("\\Z");
                    FrogConfig config = JANKSON.fromJson(scanner.next(), FrogConfig.class);
                    registerFrog(FilenameUtils.removeExtension(file.getName()
                            .toLowerCase(Locale.ROOT)), config);
                }
                catch (Exception e) {
                    LOGGER.error("Exception when registering frogs. " + e.getMessage());
                }
            }
        }
    }
}
