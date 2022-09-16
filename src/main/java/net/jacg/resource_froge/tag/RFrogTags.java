package net.jacg.resource_froge.tag;

import net.jacg.resource_froge.util.Util;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class RFrogTags {
    public static final TagKey<Block> RESOURCE_FROG_LIKES = TagKey.of(Registry.BLOCK_KEY, Util.id("resource_frog_likes"));
}
