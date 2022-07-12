package net.jacg.resource_frogs.frog;

import net.jacg.resource_frogs.config.FrogConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RFrogEntity extends FrogEntity {
    private final FrogConfig config;

    public RFrogEntity(EntityType<? extends AnimalEntity> entityType, World world, FrogConfig config) {
        super(entityType, world);
        this.config = config;
    }

    @Override
    public boolean isFireImmune() {
        return config.likesLava;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return config.breedingItem.test(stack);
    }

    //@Override
    //protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
    //    return RFrogBrain.create(this.createBrainProfile().deserialize(dynamic));
    //}
}
