package net.jacg.resource_frogs.item;

import net.jacg.resource_frogs.ResourceFrogs;
import net.jacg.resource_frogs.frog.RFrogEntity;
import net.jacg.resource_frogs.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RFItemRegistry {
    public static final Item FROG_NET = registerItem(Util.id("frog_net"), new FrogNet(new Item.Settings().group(ResourceFrogs.ITEM_GROUP).maxCount(1)));
    public static final Item FROGPEDIA = registerItem(Util.id("frogpedia"), new FrogPedia(new Item.Settings().group(ResourceFrogs.ITEM_GROUP).maxCount(1)));

    public static Item registerItem(Identifier identifier, Item item) {
        return Registry.register(Registry.ITEM, identifier, item);
    }

    public static void registerSpawnEgg(String name, EntityType<RFrogEntity> type, int primaryColor, int secondaryColor) {
        registerItem(Util.id(name), new SpawnEggItem(type, primaryColor, secondaryColor, new Item.Settings().group(ResourceFrogs.ITEM_GROUP)));
    }

    public static void registerItems() {
    }
}