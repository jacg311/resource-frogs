package net.jacg.resource_frogs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.jacg.resource_frogs.config.FrogConfig;
import net.jacg.resource_frogs.config.RFConfig;
import net.jacg.resource_frogs.frog.RFrogEntity;
import net.jacg.resource_frogs.gui.FrogPediaScreenHandler;
import net.jacg.resource_frogs.item.RFItemRegistry;
import net.jacg.resource_frogs.util.ConfigUtil;
import net.jacg.resource_frogs.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ResourceFrogs implements ModInitializer {
    public static final String MOD_ID = "resource_frogs";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(Util.id("items"), () -> new ItemStack(RFItemRegistry.FROGPEDIA));
    public static RFConfig CONFIG;
    public static List<Pair<EntityType<RFrogEntity>, FrogConfig>> FROG_LIST = new ArrayList<>();
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
