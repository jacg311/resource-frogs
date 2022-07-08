package net.jacg.resource_frogs.config;

import net.minecraft.recipe.Ingredient;

public class FrogConfig {
    public boolean likesLava = false;
    public boolean passive = false;
    public boolean hasGlowfeature = false;
    public long eatingCooldown = 600;
    public Ingredient breedingItem = Ingredient.EMPTY;
    public Ingredient foodItem = Ingredient.EMPTY;
    public SpawnEggProperties spawnEgg = new SpawnEggProperties();

    public static class SpawnEggProperties {
        public int primaryColor = 12895428;
        public int secondaryColor = 11382189;
    }
}
