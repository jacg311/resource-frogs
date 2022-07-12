package net.jacg.resource_frogs.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jacg.resource_frogs.frog.RFrogEntity;
import net.jacg.resource_frogs.util.Util;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FrogPediaScreen extends Screen {
    private static final Identifier TEXTURE = Util.id("gui/frogpedia.png");
    private final RFrogEntity frogEntity;

    public FrogPediaScreen(Text title, RFrogEntity frogEntity) {
        super(title);
        this.frogEntity = frogEntity;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        int backgroundWidth = 176;
        int backgroundHeight = 166;
        super.render(matrices, mouseX, mouseY, delta);
        DiffuseLighting.disableGuiDepthLighting();
        int width = (this.width - backgroundWidth) / 2;
        int height = (this.height - backgroundHeight) / 2;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, width, height, -500, 0, 0, backgroundWidth, backgroundHeight, 256, 256);

        InventoryScreen.drawEntity(width + 51, height + 75, 30, (float)(width + 51) - mouseX, (float)(height + 75 - 50) - mouseY, frogEntity);
    }
}
