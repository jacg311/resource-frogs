package net.jacg.resource_frogs.frog;

import net.jacg.resource_frogs.config.FrogConfig;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class RFrogEntity  extends FrogEntity {
    public final boolean LIKES_LAVA;
    public final Identifier BREEDING_ITEM;
    public final boolean HAS_GLOW_FEATURE;

    public RFrogEntity(EntityType<? extends AnimalEntity> entityType, World world, FrogConfig config) {
        super(entityType, world);
        this.LIKES_LAVA = config.likesLava;
        this.BREEDING_ITEM = new Identifier(config.breedingItem);
        this.HAS_GLOW_FEATURE = config.hasGlowFeature;
    }

    @Override
    public boolean isFireImmune() {
        return LIKES_LAVA;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        Item item = Registry.ITEM.get(BREEDING_ITEM);
        if (item == Blocks.AIR.asItem() || stack == null) return false;
        return item == stack.getItem();
    }

    //@Override
    //protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
    //    return RFrogBrain.create(this.createBrainProfile().deserialize(dynamic));
    //}
}
