package net.jacg.resource_frogs.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jacg.resource_frogs.ResourceFrogs;
import net.jacg.resource_frogs.ResourceFrogsClient;
import net.jacg.resource_frogs.frog.RFrogEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.model.FrogEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;

public class FrogPediaScreen extends HandledScreen<FrogPediaScreenHandler> {
    private final FrogEntityModel<RFrogEntity> FROG_MODEL;
    private float frogRotation = 0.0f;
    private float nextFrogRotation = 0.1f;
    private static final Identifier TEXTURE = ResourceFrogs.id("gui/frogpedia.png");
    private static final Identifier FROG_TEXTURE = ResourceFrogs.id("textures/copper_frog.png");

    public FrogPediaScreen(FrogPediaScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.FROG_MODEL = new FrogEntityModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ResourceFrogsClient.MODEL_FROG_LAYER));
        this.titleX = this.backgroundWidth / 2;
        this.playerInventoryTitleX = this.titleX;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);

        this.textRenderer.draw(matrices, String.format("%d, %d", mouseX, mouseY), mouseX + 4, mouseY + 8, 0xFFFFFF);
        this.itemRenderer.renderInGui(new ItemStack(Items.SLIME_BALL), 20, 20);



        int scale_factor = (int)this.client.getWindow().getScaleFactor();

        RenderSystem.viewport((this.width - 300) / 2 * scale_factor, (this.height - 240) / 2 * scale_factor, this.backgroundWidth, this.backgroundHeight);
        Matrix4f matrix4f = Matrix4f.translate(-0.34F, 0.23F, 0.0F);
        matrix4f.multiply(Matrix4f.viewboxMatrix(90.0, 1.3333334F, 9.0F, 80.0F));
        RenderSystem.backupProjectionMatrix();

        RenderSystem.setProjectionMatrix(matrix4f);
        matrices.push();
        matrices.translate(0, -5, 1984);
        matrices.scale(5, 5, 5);
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180));
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(20));

        float g = MathHelper.lerp(delta, this.frogRotation, this.nextFrogRotation);
        this.frogRotation = this.nextFrogRotation;
        this.nextFrogRotation += MathHelper.lerp(delta, 0, 0.1);

        float h = -(1.0F - g) * 90.0F - 90.0F;
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(h));
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-180));

        g = MathHelper.wrapDegrees(g);
        this.FROG_MODEL.getPart().yaw = g;

        VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
        VertexConsumer vertexConsumer = immediate.getBuffer(this.FROG_MODEL.getLayer(FROG_TEXTURE));
        this.FROG_MODEL.render(matrices, vertexConsumer, 0xf000f0, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
        immediate.draw();

        this.drawMouseoverTooltip(matrices, mouseX, mouseY);

        matrices.pop();
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        DrawableHelper.drawCenteredText(matrices, this.textRenderer, this.title, this.titleX, this.titleY, 0x555555);
        DrawableHelper.drawCenteredText(matrices, this.textRenderer, this.playerInventoryTitle, this.playerInventoryTitleX, this.playerInventoryTitleY, 0x555555);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        DiffuseLighting.disableGuiDepthLighting();
        int width = (this.width - this.backgroundWidth) / 2;
        int height = (this.height - this.backgroundHeight) / 2;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, width, height, -500, 0, 0, this.backgroundWidth, this.backgroundHeight, 256, 256);
    }
}
