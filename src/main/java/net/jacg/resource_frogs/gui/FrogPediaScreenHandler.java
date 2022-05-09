package net.jacg.resource_frogs.gui;

import net.jacg.resource_frogs.ResourceFrogs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class FrogPediaScreenHandler extends ScreenHandler {
    public FrogPediaScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ResourceFrogs.FROGPEDIA_SCREENHANDLER, syncId);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

}
