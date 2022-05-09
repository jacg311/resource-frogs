package net.jacg.resource_frogs.util;

import net.jacg.resource_frogs.ResourceFrogs;
import net.jacg.resource_frogs.config.RFConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigUtil {
    public static boolean configExists() {
        File configFile = ResourceFrogs.LOADER.getConfigDir().resolve(ResourceFrogs.MOD_ID).resolve("config.json").toFile();
        return configFile.exists();
    }

    public static void writeDefaultConfigFile() {
        File configFile = ResourceFrogs.LOADER.getConfigDir().resolve(ResourceFrogs.MOD_ID).resolve("config.json").toFile();
        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(ResourceFrogs.GSON.toJson(new RFConfig()));
        } catch (IOException e) {
            ResourceFrogs.LOGGER.warn(e.getMessage());
        }
    }
}