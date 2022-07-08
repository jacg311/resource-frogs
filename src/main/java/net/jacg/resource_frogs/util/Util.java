package net.jacg.resource_frogs.util;

import blue.endless.jankson.Jankson;
import com.google.gson.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.jacg.resource_frogs.ResourceFrogs;
import net.jacg.resource_frogs.config.FrogConfig;
import net.jacg.resource_frogs.frog.RFrogEntity;
import net.jacg.resource_frogs.item.RFItemRegistry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

public class Util {
    public static Identifier id(String name) {
        return new Identifier(ResourceFrogs.MOD_ID, name);
    }
    public final static Jankson JANKSON = new Jankson.Builder().build();
    public static Gson getGson() {
        return new GsonBuilder().setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Ingredient.class, ingredientJsonDeserializer)
                .create();
    }

    public static void registerFrog(String name, FrogConfig config) {
        EntityType<RFrogEntity> frog = Registry.register(Registry.ENTITY_TYPE, id(name), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType<RFrogEntity> entityType, World world) -> new RFrogEntity(entityType, world, config))
                .dimensions(new EntityDimensions(0.5f, 0.5f, true))
                .build());

        FabricDefaultAttributeRegistry.register(frog, FrogEntity.createFrogAttributes());
        RFItemRegistry.registerSpawnEgg(name + "_spawn_egg", frog, config.spawnEgg.primaryColor, config.spawnEgg.secondaryColor);
        ResourceFrogs.FROG_LIST.add(new Pair<>(frog, config));
    }

    public static void findFrogsAndRegister() {
        File[] files = FabricLoader.getInstance()
                .getConfigDir()
                .resolve(ResourceFrogs.MOD_ID)
                .resolve("frogs")
                .toFile()
                .listFiles();

        if (files == null) {
            ResourceFrogs.LOGGER.warn("No frogs to register found in " + FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve(ResourceFrogs.MOD_ID)
                    .resolve("frogs"));
        }
        else {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)) {
                    scanner.useDelimiter("\\Z");
                    FrogConfig config = JANKSON.fromJson(scanner.next(), FrogConfig.class);
                    registerFrog(FilenameUtils.removeExtension(file.getName()
                            .toLowerCase(Locale.ENGLISH)), config);
                }
                catch (Exception e) {
                    ResourceFrogs.LOGGER.error("Exception when registering frogs. " + e.getMessage());
                }
            }
        }
    }

    public static JsonDeserializer<Ingredient> ingredientJsonDeserializer = (json, typeOfT, context) -> {
        List<Item> items = new ArrayList<>();
        if (json.isJsonArray()) {
            json.getAsJsonArray().forEach(element -> items.add(Registry.ITEM.get(new Identifier(element.getAsString()))));
        }
        else {
            items.add(Registry.ITEM.get(new Identifier(json.getAsString())));
        }

        return Ingredient.ofStacks(items.stream()
                .map(ItemStack::new));
    };
}
