package dev.akarah.monomial.api.value;

public class ResourceKey<T> {
    String namespace;
    String path;

    private ResourceKey(String namespace, String path) {
        this.namespace = namespace;
        this.path = path;
    }

    public static <T> ResourceKey<T> create(String namespace, String path) {
        return new ResourceKey<>(namespace, path);
    }

    public static <T> ResourceKey<T> fromString(String string) {
        if(string.contains(":")) {
            var split = string.split(":");
            return new ResourceKey<>(split[0], split[1]);
        }
        return new ResourceKey<>("minecraft", string);
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

    public ResourceLocation asResourceLocation() {
        return ResourceLocation.create(this.namespace(), this.path());
    }
}
