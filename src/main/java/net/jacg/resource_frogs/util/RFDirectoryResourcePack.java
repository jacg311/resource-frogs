package net.jacg.resource_frogs.util;

import net.jacg.resource_frogs.ResourceFrogs;
import net.minecraft.resource.AbstractFileResourcePack;
import net.minecraft.resource.DirectoryResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Predicate;

public class RFDirectoryResourcePack extends DirectoryResourcePack {

    public RFDirectoryResourcePack(File file) {
        super(file);
    }

    @Override
    protected boolean containsFile(String name) {
        try {
            /* if the game is looking for assets in the resource_frogs namespace, shorten the needed path
             * to allow a flat resourcepack structure.
             + example like it is used here:
             * config/resource_frogs
             *  - frogs
             *  - textures
             *      - copper_frog.png
             *
             * this might have issues with eg. lang files. needs fixing.
             */
            name = name.replaceFirst("assets/resource_frogs/", "") + ".png";
            File file = new File(this.base, name);
            if (file.isFile() && DirectoryResourcePack.isValidPath(file, name)) return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @Override
    protected InputStream openFile(String name) throws IOException {
        name = name + ".png";
        File file = new File(this.base, name);
        return new FileInputStream(file);
    }

    @Override
    public InputStream open(ResourceType type, Identifier id) throws IOException {
        return this.openFile(type.getDirectory().replaceFirst("assets", "") + id.getPath());
    }

    @Override
    public Set<String> getNamespaces(ResourceType type) {
        // "existing namespaces" are hard coded since only resource_frogs is expected
        Set<String> ret = new HashSet<>();
        if (type == ResourceType.CLIENT_RESOURCES) ret.add("resource_frogs");
        return ret;
    }

    @Override
    public Collection<Identifier> findResources(ResourceType type, String namespace, String prefix, Predicate<Identifier> allowedPathPredicate) {
        List<Identifier> identifiers = new ArrayList<>();
        System.out.println(prefix);
        /* ignore everything that arent client resources or that are fonts.
         * no need to check for those because they cant exist in this context
        */
        if ((type == ResourceType.CLIENT_RESOURCES) && !prefix.equals("font")) {
            File[] files = this.base.listFiles((file, s) -> {
                s = s.toLowerCase(Locale.ENGLISH);
                return s.endsWith(".png") || s.endsWith(".mcmeta");
            });

            if (files != null) {
                for (File file : files) {
                    identifiers.add(ResourceFrogs.id("textures/" + FilenameUtils.removeExtension(file.getName())));
                }
            }
        }
        return identifiers;
    }

    @Nullable
    @Override
    public <T> T parseMetadata(ResourceMetadataReader<T> metaReader) {
        // provides the usual pack data you can find in all resourcepacks.
        InputStream stream = new ByteArrayInputStream("{\"pack\":{\"description\":\"Textures for Resource Frogs\",\"pack_format\":9}}".getBytes(StandardCharsets.UTF_8));

        return AbstractFileResourcePack.parseMetadata(metaReader, stream);
    }

    @Override
    public String getName() {
        return "Resource Frogs";
    }
}
