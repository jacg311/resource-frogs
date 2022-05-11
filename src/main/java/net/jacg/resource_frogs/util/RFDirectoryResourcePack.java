package net.jacg.resource_frogs.util;

import net.jacg.resource_frogs.ResourceFrogs;
import net.minecraft.resource.AbstractFileResourcePack;
import net.minecraft.resource.DirectoryResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

public class RFDirectoryResourcePack extends DirectoryResourcePack {
    private final Path texturePath;
    private final AutoCloseable closer;
    public RFDirectoryResourcePack(Path file, AutoCloseable closer) {
        super(file.toFile());
        this.texturePath = file;
        this.closer = closer;
    }

    @Override
    protected boolean containsFile(String name) {
        name = name.replaceFirst("assets/resource_frogs/", "");
        File file = new File(this.base, name);
        try {
            if (file.isFile() && DirectoryResourcePack.isValidPath(file, name)) return true;
        } catch (IOException ignored) {
        }

        return false;
    }

    @Override
    protected InputStream openFile(String name) throws IOException {
        name = name.replaceFirst("assets/resource_frogs/", "");
        return new FileInputStream(new File(this.base, name));
    }

    @Override
    public InputStream open(ResourceType type, Identifier id) throws IOException {
        return this.openFile(type.getDirectory().replaceFirst("assets", "") + id.getPath());
    }

    @Override
    public Set<String> getNamespaces(ResourceType type) {
        Set<String> ret = new HashSet<>();
        if (type == ResourceType.CLIENT_RESOURCES) ret.add("resource_frogs");
        return ret;
    }

    @Override
    public Collection<Identifier> findResources(ResourceType type, String namespace, String prefix, Predicate<Identifier> allowedPathPredicate) {
        List<Identifier> identifiers = new ArrayList<>();
        if (prefix.equals("font")) return identifiers;

        File[] files = this.texturePath.resolve("textures").toFile().listFiles((file, s) -> {
            s = s.toLowerCase(Locale.ENGLISH);
            return s.endsWith(".png") || s.endsWith(".mcmeta");
        });

        if (files != null) {
            for (File file : files) {
                identifiers.add(ResourceFrogs.id("textures/" + file.getName()));
            }
        }
        return identifiers;
    }

    @Nullable
    @Override
    public <T> T parseMetadata(ResourceMetadataReader<T> metaReader) {
        InputStream stream = new ByteArrayInputStream("{\"pack\":{\"description\":\"Textures for Resource Frogs\",\"pack_format\":9}}".getBytes(StandardCharsets.UTF_8));
        return AbstractFileResourcePack.parseMetadata(metaReader, stream);
    }

    @Override
    public String getName() {
        return "Resource Frogs";
    }

    @Override
    public void close() {
        if (closer != null) {
            try {
                closer.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
