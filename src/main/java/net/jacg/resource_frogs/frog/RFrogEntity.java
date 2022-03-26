package net.jacg.resource_frogs.frog;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class RFrogEntity  extends FrogEntity {
    public final boolean IMMUNE_TO_FIRE = false;
    public final ItemStack BREEDING_ITEM = Items.SLIME_BALL.getDefaultStack();
    public final boolean HAS_GLOW_FEATURE = false;

    public RFrogEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean isOnFire() {
        return IMMUNE_TO_FIRE;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        if (BREEDING_ITEM == null || stack == null) return false;
        return BREEDING_ITEM.isItemEqual(stack);
    }

}
