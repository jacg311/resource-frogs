package net.jacg.resource_frogs.item;

import net.jacg.resource_frogs.gui.FrogPediaScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FrogPedia extends Item implements NamedScreenHandlerFactory {
    public FrogPedia(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            user.openHandledScreen(this);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("resource_frogs.gui.frogpedia");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new FrogPediaScreenHandler(syncId, inv);
    }
}
