package net.jacg.resource_frogs.frog;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.FrogAnimations;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.FrogEntityModel;
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
    private final ModelPart croakingBody;

    public RFrogEntityModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.body.getChild("head");
        this.eyes = this.head.getChild("eyes");
        this.tongue = this.body.getChild("tongue");
        this.leftArm = this.body.getChild("left_arm");
        this.rightArm = this.body.getChild("right_arm");
        this.leftLeg = this.root.getChild("left_leg");
        this.rightLeg = this.root.getChild("right_leg");
        this.croakingBody = this.body.getChild("croaking_body");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData2 = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        ModelPartData modelPartData3 = modelPartData2.addChild("body", ModelPartBuilder.create().uv(3, 1).cuboid(-3.5F, -2.0F, -8.0F, 7.0F, 3.0F, 9.0F).uv(23, 22).cuboid(-3.5F, -1.0F, -8.0F, 7.0F, 0.0F, 9.0F), ModelTransform.pivot(0.0F, -2.0F, 4.0F));
        ModelPartData modelPartData4 = modelPartData3.addChild("head", ModelPartBuilder.create().uv(23, 13).cuboid(-3.5F, -1.0F, -7.0F, 7.0F, 0.0F, 9.0F).uv(0, 13).cuboid(-3.5F, -2.0F, -7.0F, 7.0F, 3.0F, 9.0F), ModelTransform.pivot(0.0F, -2.0F, -1.0F));
        ModelPartData modelPartData5 = modelPartData4.addChild("eyes", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, 0.0F, 2.0F));
        modelPartData5.addChild("right_eye", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F), ModelTransform.pivot(-1.5F, -3.0F, -6.5F));
        modelPartData5.addChild("left_eye", ModelPartBuilder.create().uv(0, 5).cuboid(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F), ModelTransform.pivot(2.5F, -3.0F, -6.5F));
        modelPartData3.addChild("croaking_body", ModelPartBuilder.create().uv(26, 5).cuboid(-3.5F, -0.1F, -2.9F, 7.0F, 2.0F, 3.0F, new Dilation(-0.1F)), ModelTransform.pivot(0.0F, -1.0F, -5.0F));
        ModelPartData modelPartData6 = modelPartData3.addChild("tongue", ModelPartBuilder.create().uv(17, 13).cuboid(-2.0F, 0.0F, -7.1F, 4.0F, 0.0F, 7.0F), ModelTransform.pivot(0.0F, -1.01F, 1.0F));
        ModelPartData modelPartData7 = modelPartData3.addChild("left_arm", ModelPartBuilder.create().uv(0, 32).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 3.0F), ModelTransform.pivot(4.0F, -1.0F, -6.5F));
        modelPartData7.addChild("left_hand", ModelPartBuilder.create().uv(18, 40).cuboid(-4.0F, 0.01F, -4.0F, 8.0F, 0.0F, 8.0F), ModelTransform.pivot(0.0F, 3.0F, -1.0F));
        ModelPartData modelPartData8 = modelPartData3.addChild("right_arm", ModelPartBuilder.create().uv(0, 38).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 3.0F), ModelTransform.pivot(-4.0F, -1.0F, -6.5F));
        modelPartData8.addChild("right_hand", ModelPartBuilder.create().uv(2, 40).cuboid(-4.0F, 0.01F, -5.0F, 8.0F, 0.0F, 8.0F), ModelTransform.pivot(0.0F, 3.0F, 0.0F));
        ModelPartData modelPartData9 = modelPartData2.addChild("left_leg", ModelPartBuilder.create().uv(14, 25).cuboid(-1.0F, 0.0F, -2.0F, 3.0F, 3.0F, 4.0F), ModelTransform.pivot(3.5F, -3.0F, 4.0F));
        modelPartData9.addChild("left_foot", ModelPartBuilder.create().uv(2, 32).cuboid(-4.0F, 0.01F, -4.0F, 8.0F, 0.0F, 8.0F), ModelTransform.pivot(2.0F, 3.0F, 0.0F));
        ModelPartData modelPartData10 = modelPartData2.addChild("right_leg", ModelPartBuilder.create().uv(0, 25).cuboid(-2.0F, 0.0F, -2.0F, 3.0F, 3.0F, 4.0F), ModelTransform.pivot(-3.5F, -3.0F, 4.0F));
        modelPartData10.addChild("right_foot", ModelPartBuilder.create().uv(18, 32).cuboid(-4.0F, 0.01F, -4.0F, 8.0F, 0.0F, 8.0F), ModelTransform.pivot(-2.0F, 3.0F, 0.0F));
        return TexturedModelData.of(modelData, 48, 48);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(T baseFrogEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        float k = Math.min((float)baseFrogEntity.getVelocity().lengthSquared() * 200.0F, 8.0F);
        this.method_43781(baseFrogEntity.longJumpingAnimationState, FrogAnimations.LONG_JUMPING);
        this.method_43781(baseFrogEntity.croakingAnimationState, FrogAnimations.CROAKING);
        this.method_43781(baseFrogEntity.usingTongueAnimationState, FrogAnimations.USING_TONGUE);
        this.method_43782(baseFrogEntity.walkingAnimationState, FrogAnimations.WALKING, k);
        this.method_43781(baseFrogEntity.swimmingAnimationState, FrogAnimations.SWIMMING);
        this.method_43781(baseFrogEntity.idlingInWaterAnimationState, FrogAnimations.IDLING_IN_WATER);
        this.croakingBody.visible = baseFrogEntity.croakingAnimationState.isRunning();
    }

    public void setRootPitch(float pitch) {
        this.root.pitch = pitch;
    }

    public void setRootYaw(float yaw) {
        this.root.yaw = yaw;
    }

    public void printPivot() {
        System.out.println(this.root.pivotX);
        System.out.println(this.root.pivotY);
        System.out.println(this.root.pivotZ);
    }
}
