package net.jacg.resource_froge;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.jacg.resource_froge.config.FrogConfig;
import net.jacg.resource_froge.config.RFConfig;
import net.jacg.resource_froge.frog.RFrogEntity;
import net.jacg.resource_froge.gui.FrogPediaScreenHandler;
import net.jacg.resource_froge.item.RFItemRegistry;
import net.jacg.resource_froge.util.ConfigUtil;
import net.jacg.resource_froge.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ResourceFroge implements ModInitializer {
    public static final String MOD_ID = "resource_froge";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(Util.id("items"), () -> new ItemStack(RFItemRegistry.FROGPEDIA));
    public static RFConfig CONFIG;
    public static final Map<EntityType<RFrogEntity>, FrogConfig> FROG_CONFIG_HASH_MAP = new HashMap<>();
    public static final ScreenHandlerType<FrogPediaScreenHandler> FROGPEDIA_SCREENHANDLER = Registry.register(Registry.SCREEN_HANDLER, Util.id("frogpedia"), new ScreenHandlerType<>(FrogPediaScreenHandler::new));

    @Override
    public void onInitialize() {
        if (!ConfigUtil.configExists()) {
            ConfigUtil.createConfigFolders();
            ConfigUtil.writeDefaultConfigFile();
        }

        CONFIG = ConfigUtil.readConfigOrDefault();

        Util.findFrogsAndRegister();
        RFItemRegistry.registerItems();
    }
}
