package net.jacg.resource_frogs.config;

public class FrogConfig {
    public boolean likesLava = false;
    public String breedingItem = "minecraft:slime_ball";
    public boolean hasGlowFeature = false;
    public SpawnEggProperties spawnEgg = new SpawnEggProperties();

    public static class SpawnEggProperties {
        public int primaryColor = 12895428;
        public int secondaryColor = 11382189;
    }
}
