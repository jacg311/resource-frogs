package net.jacg.resource_frogs.frog;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.FrogAnimations;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.AnimationState;
import net.minecraft.util.Util;
import net.minecraft.util.math.Vec3f;

public class RFrogEntityModel<T extends RFrogEntity>
        extends SinglePartEntityModel<T> {
    private static final Vec3f direction = new Vec3f();
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart eyes;
    private final ModelPart tongue;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public RFrogEntityModel(ModelPart root) {
        this.root = root.getChild(EntityModelPartNames.ROOT);
        this.body = this.root.getChild(EntityModelPartNames.BODY);
        this.head = this.body.getChild(EntityModelPartNames.HEAD);
        this.eyes = this.head.getChild(EntityModelPartNames.EYES);
        this.tongue = this.body.getChild(EntityModelPartNames.TONGUE);
        this.leftArm = this.body.getChild(EntityModelPartNames.LEFT_ARM);
        this.rightArm = this.body.getChild(EntityModelPartNames.RIGHT_ARM);
        this.leftLeg = this.root.getChild(EntityModelPartNames.LEFT_LEG);
        this.rightLeg = this.root.getChild(EntityModelPartNames.RIGHT_LEG);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData2 = modelPartData.addChild(EntityModelPartNames.ROOT, ModelPartBuilder.create(), ModelTransform.pivot(0.0f, 24.0f, 0.0f));
        ModelPartData modelPartData3 = modelPartData2.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(3, 1).cuboid(-3.5f, -2.0f, -8.0f, 7.0f, 3.0f, 9.0f).uv(23, 22).cuboid(-3.5f, -1.0f, -8.0f, 7.0f, 0.0f, 9.0f), ModelTransform.pivot(0.0f, -2.0f, 4.0f));
        ModelPartData modelPartData4 = modelPartData3.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(23, 13).cuboid(-3.5f, -1.0f, -7.0f, 7.0f, 0.0f, 9.0f).uv(0, 13).cuboid(-3.5f, -2.0f, -7.0f, 7.0f, 3.0f, 9.0f), ModelTransform.pivot(0.0f, -2.0f, -1.0f));
        ModelPartData modelPartData5 = modelPartData4.addChild(EntityModelPartNames.EYES, ModelPartBuilder.create(), ModelTransform.pivot(-0.5f, 0.0f, 2.0f));
        modelPartData5.addChild(EntityModelPartNames.RIGHT_EYE, ModelPartBuilder.create().uv(0, 0).cuboid(-1.5f, -1.0f, -1.5f, 3.0f, 2.0f, 3.0f), ModelTransform.pivot(-1.5f, -3.0f, -6.5f));
        modelPartData5.addChild(EntityModelPartNames.LEFT_EYE, ModelPartBuilder.create().uv(0, 5).cuboid(-1.5f, -1.0f, -1.5f, 3.0f, 2.0f, 3.0f), ModelTransform.pivot(2.5f, -3.0f, -6.5f));
        modelPartData3.addChild(EntityModelPartNames.CROAKING_BODY, ModelPartBuilder.create().uv(26, 5).cuboid(-3.5f, -0.1f, -2.9f, 7.0f, 2.0f, 3.0f, new Dilation(-0.1f)), ModelTransform.pivot(0.0f, -1.0f, -5.0f));
        ModelPartData modelPartData6 = modelPartData3.addChild(EntityModelPartNames.TONGUE, ModelPartBuilder.create(), ModelTransform.pivot(0.0f, -1.0f, 1.0f));
        modelPartData6.addChild(EntityModelPartNames.TONGUE_RL, ModelPartBuilder.create().uv(24, 13).cuboid(-2.0f, -7.0f, 0.1f, 4.0f, 7.0f, 0.0f), ModelTransform.of(0.0f, 0.0f, 0.0f, 1.5708f, 0.0f, 0.0f));
        ModelPartData modelPartData7 = modelPartData3.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(0, 32).cuboid(-1.0f, 0.0f, -1.0f, 2.0f, 3.0f, 3.0f), ModelTransform.pivot(4.0f, -1.0f, -6.5f));
        modelPartData7.addChild(EntityModelPartNames.LEFT_HAND, ModelPartBuilder.create().uv(18, 40).cuboid(-4.0f, 0.0f, -4.0f, 8.0f, 0.0f, 8.0f), ModelTransform.pivot(0.0f, 3.0f, -1.0f));
        ModelPartData modelPartData8 = modelPartData3.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(0, 38).cuboid(-1.0f, 0.0f, -1.0f, 2.0f, 3.0f, 3.0f), ModelTransform.pivot(-4.0f, -1.0f, -6.5f));
        modelPartData8.addChild(EntityModelPartNames.RIGHT_HAND, ModelPartBuilder.create().uv(2, 40).cuboid(-4.0f, 3.0f, -5.0f, 8.0f, 0.0f, 8.0f), ModelTransform.pivot(0.0f, 0.0f, 0.0f));
        ModelPartData modelPartData9 = modelPartData2.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(14, 25).cuboid(-1.0f, 0.0f, -2.0f, 3.0f, 3.0f, 4.0f), ModelTransform.pivot(3.5f, -3.0f, 4.0f));
        modelPartData9.addChild(EntityModelPartNames.LEFT_FOOT, ModelPartBuilder.create().uv(2, 32).cuboid(-4.0f, 0.0f, -4.0f, 8.0f, 0.0f, 8.0f), ModelTransform.pivot(2.0f, 3.0f, 0.0f));
        ModelPartData modelPartData10 = modelPartData2.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 25).cuboid(-2.0f, 0.0f, -2.0f, 3.0f, 3.0f, 4.0f), ModelTransform.pivot(-3.5f, -3.0f, 4.0f));
        modelPartData10.addChild(EntityModelPartNames.RIGHT_FOOT, ModelPartBuilder.create().uv(18, 32).cuboid(-4.0f, 0.0f, -4.0f, 8.0f, 0.0f, 8.0f), ModelTransform.pivot(-2.0f, 3.0f, 0.0f));
        return TexturedModelData.of(modelData, 48, 48);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(T baseFrogEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        long l = Util.getMeasuringTimeMs();
        this.runAnimation(((RFrogEntity)baseFrogEntity).longJumping, FrogAnimations.LONG_JUMPING, l);
        this.runAnimation(((RFrogEntity)baseFrogEntity).croaking, FrogAnimations.CROAKING, l);
        this.runAnimation(((RFrogEntity)baseFrogEntity).usingTongue, FrogAnimations.USING_TONGUE, l);
        this.runAnimation(((RFrogEntity)baseFrogEntity).walking, FrogAnimations.WALKING, l);
        this.runAnimation(((RFrogEntity)baseFrogEntity).swimming, FrogAnimations.SWIMMING, l);
        this.runAnimation(((RFrogEntity)baseFrogEntity).idlingInWater, FrogAnimations.IDLING_IN_WATER, l);
    }

    private void runAnimation(AnimationState animationState, Animation animation, long time) {
        animationState.run(state -> AnimationHelper.animate(this, animation, time - state.getStartTime(), 1.0f, direction));
    }
}
