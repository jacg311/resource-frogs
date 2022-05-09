package net.jacg.resource_frogs;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.jacg.resource_frogs.config.FrogConfig;
import net.jacg.resource_frogs.frog.RFrogEntity;
import net.jacg.resource_frogs.gui.FrogPediaScreenHandler;
import net.jacg.resource_frogs.item.FrogNet;
import net.jacg.resource_frogs.item.FrogPedia;
import net.jacg.resource_frogs.util.ConfigUtil;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ResourceFrogs implements ModInitializer {
    // TODO: Clean up registering
    public static final String MOD_ID = "resource_frogs";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final FabricLoader LOADER = FabricLoader.getInstance();
    public static final Gson GSON = new GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

    public static List<Pair<EntityType<RFrogEntity>, FrogConfig>> FROG_LIST = new ArrayList<>();

    public static final Item FROG_NET = Registry.register(Registry.ITEM, id("frog_net"), new FrogNet(new Item.Settings().maxCount(1)));
    public static final Item FROGPEDIA = Registry.register(Registry.ITEM, id("frogpedia"), new FrogPedia(new Item.Settings().maxCount(1)));

    public static final ScreenHandlerType<FrogPediaScreenHandler> FROGPEDIA_SCREENHANDLER = Registry.register(Registry.SCREEN_HANDLER, id("frogpedia"), new ScreenHandlerType<>(FrogPediaScreenHandler::new));
    @Override
    public void onInitialize() {
        if (!ConfigUtil.configExists()) {
            ConfigUtil.writeDefaultConfigFile();
        }

        File[] files = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID).resolve("frogs").toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)){
                    scanner.useDelimiter("\\Z");
                    FrogConfig config = GSON.fromJson(scanner.next(), FrogConfig.class);
                    registerFrog(FilenameUtils.removeExtension(file.getName().toLowerCase(Locale.ENGLISH)), config);
                } catch (Exception e) {
                    LOGGER.error("Exception when registering frogs. " + e.getMessage());
                }
            }
        }
    }

    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }

    public static void registerFrog(String name, FrogConfig config) {
        EntityType<RFrogEntity> frog = Registry.register(Registry.ENTITY_TYPE, id(name),
                FabricEntityTypeBuilder
                        .create(SpawnGroup.CREATURE, (EntityType<RFrogEntity> entityType, World world) -> new RFrogEntity(entityType, world, config))
                        .dimensions(new EntityDimensions(0.5f, 0.5f, true))
                        .build());

        FabricDefaultAttributeRegistry.register(frog, FrogEntity.createFrogAttributes());
        registerSpawnEgg(name + "_spawn_egg", frog, 12895428, 11382189);
        FROG_LIST.add(new Pair<>(frog, config));
    }

    public static void registerSpawnEgg(String name, EntityType<RFrogEntity> type, int primaryColor, int secondaryColor) {
        Registry.register(Registry.ITEM, id(name), new SpawnEggItem(type, primaryColor, secondaryColor, new Item.Settings().group(ItemGroup.MISC)));
    }
}
