package net.jacg.resource_frogs.frog;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class RFrogEntity  extends FrogEntity {
    public final boolean LIKES_LAVA = false;
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
        return LIKES_LAVA;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        if (BREEDING_ITEM == null || stack == null) return false;
        return BREEDING_ITEM.isItemEqual(stack);
    }

}
