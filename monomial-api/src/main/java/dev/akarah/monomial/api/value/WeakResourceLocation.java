package dev.akarah.monomial.api.value;

import com.mojang.serialization.Codec;

public record WeakResourceLocation(String namespace, String path) implements ResourceLocation {
    public static Codec<WeakResourceLocation> CODEC = Codec.STRING.xmap(WeakResourceLocation::fromString, WeakResourceLocation::toString);

    public static WeakResourceLocation fromString(String string) {
        if(string.contains(":")) {
            var split = string.split(":");
            return new WeakResourceLocation(split[0], split[1]);
        }
        return new WeakResourceLocation("minecraft", string);
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }
}
