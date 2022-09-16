package net.jacg.resource_froge.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class FrogNet extends Item {
    public FrogNet(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getWorld().isClient()) {
            NbtCompound nbt = stack.getOrCreateNbt();
            if (entity instanceof FrogEntity frogEntity && !nbt.getBoolean("HasFrog")) {
                frogEntity.remove(Entity.RemovalReason.DISCARDED);
                nbt.putBoolean("HasFrog", true);
                stack.writeNbt(nbt);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
