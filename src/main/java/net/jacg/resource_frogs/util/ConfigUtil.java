package net.jacg.resource_frogs.util;

import net.jacg.resource_frogs.ResourceFrogs;
import net.jacg.resource_frogs.config.RFConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ConfigUtil {
    private static File getConfigFile() {
        return ResourceFrogs.LOADER.getConfigDir().resolve(ResourceFrogs.MOD_ID).resolve("config.json").toFile();
    }
    public static boolean configExists() {
        return getConfigFile().exists();
    }

    public static void writeDefaultConfigFile() {
        File configFile = getConfigFile();
        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(ResourceFrogs.GSON.toJson(new RFConfig()));
        } catch (IOException e) {
            ResourceFrogs.LOGGER.warn(e.getMessage());
        }
    }

    public static RFConfig readConfigOrDefault() {
        File configFile = getConfigFile();
        try (Scanner scanner = new Scanner(configFile)) {
            scanner.useDelimiter("\\Z");
            return ResourceFrogs.GSON.fromJson(scanner.next(), RFConfig.class);
        }
        catch (FileNotFoundException e) {
            return new RFConfig();
        }
    }

    public static void createConfigFolders() {
        Path basePath = ResourceFrogs.LOADER.getConfigDir().resolve(ResourceFrogs.MOD_ID);
        if (!basePath.toFile().mkdirs()) {
            ResourceFrogs.LOGGER.error("Could not create config folder.");
            return;
        }

        basePath.resolve("frogs").toFile().mkdirs();
        basePath.resolve("lang").toFile().mkdirs();
        basePath.resolve("textures").toFile().mkdirs();
    }
}