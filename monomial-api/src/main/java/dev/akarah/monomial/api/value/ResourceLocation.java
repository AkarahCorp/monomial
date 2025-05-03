package dev.akarah.monomial.api.value;

import com.mojang.serialization.Codec;

public class ResourceLocation {
    String namespace;
    String path;

    private ResourceLocation(String namespace, String path) {
        this.namespace = namespace;
        this.path = path;
    }

    public static Codec<ResourceLocation> CODEC = Codec.STRING.xmap(ResourceLocation::fromString, ResourceLocation::toString);

    public static ResourceLocation create(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }

    public static ResourceLocation fromString(String string) {
        if(string.contains(":")) {
            var split = string.split(":");
            return new ResourceLocation(split[0], split[1]);
        }
        return new ResourceLocation("minecraft", string);
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }

    public String namespace() {
        return this.namespace;
    }

    public String path() {
        return this.path;
    }
}
