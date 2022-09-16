package net.jacg.resource_froge.frog;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class RFrogBrain {
    private static final UniformIntProvider bigJumpCooldownRange = UniformIntProvider.create(100, 140);

    private static void addCoreActivities(Brain<RFrogEntity> brain) {
        brain.setTaskList(Activity.CORE, 0, ImmutableList.of(new WalkTask(2.0f), new LookAroundTask(45, 90), new WanderAroundTask(), new TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS), new TemptationCooldownTask(MemoryModuleType.LONG_JUMP_COOLING_DOWN)));
        brain.setTaskList(Activity.IDLE, ImmutableList.of(Pair.of(0, new TimeLimitedTask<LivingEntity>(new FollowMobTask(EntityType.PLAYER, 6.0f), UniformIntProvider.create(30, 60))), Pair.of(0, new BreedTask(EntityType.FROG, 1.0f)), Pair.of(1, new TemptTask(frog -> 1.25f)), Pair.of(2, new UpdateAttackTargetTask<>(RFrogBrain::isNotBreeding, frog -> frog.getBrain()
                .getOptionalMemory(MemoryModuleType.NEAREST_ATTACKABLE))), Pair.of(3, new WalkTowardsLandTask(6, 1.0f)), Pair.of(4, new RandomTask(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT), ImmutableList.of(Pair.of(new StrollTask(1.0f), 1), Pair.of(new GoTowardsLookTarget(1.0f, 3), 1), Pair.of(new CroakTask(), 3), Pair.of(new ConditionalTask<>(Entity::isOnGround, new WaitTask(5, 20)), 2))))), ImmutableSet.of(Pair.of(MemoryModuleType.LONG_JUMP_MID_JUMP, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.IS_IN_WATER, MemoryModuleState.VALUE_ABSENT)));
        brain.setTaskList(Activity.SWIM, ImmutableList.of(Pair.of(0, new TimeLimitedTask<LivingEntity>(new FollowMobTask(EntityType.PLAYER, 6.0f), UniformIntProvider.create(30, 60))), Pair.of(1, new TemptTask(frog -> 1.25f)), Pair.of(2, new UpdateAttackTargetTask<>(RFrogBrain::isNotBreeding, frog -> frog.getBrain()
                .getOptionalMemory(MemoryModuleType.NEAREST_ATTACKABLE))), Pair.of(3, new WalkTowardsLandTask(8, 1.5f)), Pair.of(5, new CompositeTask(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT), ImmutableSet.of(), CompositeTask.Order.ORDERED, CompositeTask.RunMode.TRY_ALL, ImmutableList.of(Pair.of(new AquaticStrollTask(0.75f), 1), Pair.of(new StrollTask(1.0f, true), 1), Pair.of(new GoTowardsLookTarget(1.0f, 3), 1), Pair.of(new ConditionalTask<>(Entity::isInsideWaterOrBubbleColumn, new WaitTask(30, 60)), 5))))), ImmutableSet.of(Pair.of(MemoryModuleType.LONG_JUMP_MID_JUMP, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.IS_IN_WATER, MemoryModuleState.VALUE_PRESENT)));
        brain.setTaskList(Activity.LAY_SPAWN, ImmutableList.of(Pair.of(0, new TimeLimitedTask<LivingEntity>(new FollowMobTask(EntityType.PLAYER, 6.0f), UniformIntProvider.create(30, 60))), Pair.of(1, new UpdateAttackTargetTask<>(RFrogBrain::isNotBreeding, frog -> frog.getBrain()
                .getOptionalMemory(MemoryModuleType.NEAREST_ATTACKABLE))), Pair.of(2, new WalkTowardsWaterTask(8, 1.0f)), Pair.of(3, new LayFrogSpawnTask(Blocks.FROGSPAWN, MemoryModuleType.IS_PREGNANT)), Pair.of(4, new RandomTask(ImmutableList.of(Pair.of(new StrollTask(1.0f), 2), Pair.of(new GoTowardsLookTarget(1.0f, 3), 1), Pair.of(new CroakTask(), 2), Pair.of(new ConditionalTask<>(Entity::isOnGround, new WaitTask(5, 20)), 1))))), ImmutableSet.of(Pair.of(MemoryModuleType.LONG_JUMP_MID_JUMP, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.IS_PREGNANT, MemoryModuleState.VALUE_PRESENT)));
        brain.setTaskList(Activity.LONG_JUMP, ImmutableList.of(Pair.of(0, new LeapingChargeTask(bigJumpCooldownRange, SoundEvents.ENTITY_FROG_STEP)), Pair.of(1, new BiasedLongJumpTask<>(bigJumpCooldownRange, 2, 4, 1.5f, frog -> SoundEvents.ENTITY_FROG_LONG_JUMP, BlockTags.FROG_PREFER_JUMP_TO, 0.5f, state -> state.isOf(Blocks.LILY_PAD)))), ImmutableSet.of(Pair.of(MemoryModuleType.BREED_TARGET, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.LONG_JUMP_COOLING_DOWN, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.IS_IN_WATER, MemoryModuleState.VALUE_ABSENT)));
        //brain.setTaskList(Activity.TONGUE, 0, ImmutableList.of(new ForgetAttackTargetTask(), new RFrogEatEntityTask(SoundEvents.ENTITY_FROG_TOUNGE, SoundEvents.ENTITY_FROG_EAT)), MemoryModuleType.ATTACK_TARGET);
    }

    private static boolean isNotBreeding(RFrogEntity frog) {
        return !LookTargetUtil.hasBreedTarget(frog);
    }
}
