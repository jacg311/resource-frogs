package net.jacg.resource_frogs.tag;

import net.jacg.resource_frogs.ResourceFrogs;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class RFrogTags {
    public static final TagKey<Block> RESOURCE_FROG_LIKES = TagKey.of(Registry.BLOCK_KEY, ResourceFrogs.id("resource_frog_likes"));
}
