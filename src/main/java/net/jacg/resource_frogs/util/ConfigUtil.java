package net.jacg.resource_frogs.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.jacg.resource_frogs.ResourceFrogs;
import net.jacg.resource_frogs.config.RFConfig;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class ConfigUtil {
    private static File getConfigFile() {
        return FabricLoader.getInstance()
                .getConfigDir()
                .resolve(ResourceFrogs.MOD_ID)
                .resolve("config.json")
                .toFile();
    }

    private static Gson getGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();
    }

    public static boolean configExists() {
        return getConfigFile().exists();
    }

    public static void writeDefaultConfigFile() {
        Gson gson = getGson();

        File configFile = getConfigFile();
        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(gson.toJson(new RFConfig()));
        }
        catch (IOException e) {
            ResourceFrogs.LOGGER.warn(e.getMessage());
        }
    }

    public static RFConfig readConfigOrDefault() {
        Gson gson = getGson();

        File configFile = getConfigFile();
        try (FileReader reader = new FileReader(configFile)) {
            return gson.fromJson(reader, RFConfig.class);
        }
        catch (IOException e) {
            ResourceFrogs.LOGGER.error("Error while reading file, using default: " + e);
            return new RFConfig();
        }
    }

    public static void createConfigFolders() {
        Path basePath = FabricLoader.getInstance()
                .getConfigDir()
                .resolve(ResourceFrogs.MOD_ID);
        if (!basePath.toFile()
                .mkdirs()) {
            ResourceFrogs.LOGGER.error("Could not create config folder.");
            return;
        }

        Arrays.asList("config", "frog", "lang")
                .forEach(subfolder -> {
                    Path subPath = basePath.resolve(subfolder);
                    if (!subPath.toFile()
                            .mkdirs()) {
                        ResourceFrogs.LOGGER.error("Could not create subfolder: " + subfolder);
                    }
                });
    }
}