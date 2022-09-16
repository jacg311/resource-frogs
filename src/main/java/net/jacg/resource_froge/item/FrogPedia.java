package net.jacg.resource_froge.item;

import net.jacg.resource_froge.frog.RFrogEntity;
import net.jacg.resource_froge.gui.FrogPediaScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class FrogPedia extends Item {
    public FrogPedia(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user.world.isClient && entity instanceof RFrogEntity frogEntity) {
            MinecraftClient.getInstance().setScreen(new FrogPediaScreen(Text.translatable("resource_frogs.gui.frogpedia"), frogEntity));
        }
        return ActionResult.SUCCESS;
    }
}
