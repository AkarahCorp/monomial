package dev.akarah.monomial.api.value;

public record TypedResourceLocation<T>(String namespace, String path) implements ResourceLocation {
    public static <T> TypedResourceLocation<T> fromString(String string) {
        if(string.contains(":")) {
            var split = string.split(":");
            return new TypedResourceLocation<>(split[0], split[1]);
        }
        return new TypedResourceLocation<>("minecraft", string);
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }
}
