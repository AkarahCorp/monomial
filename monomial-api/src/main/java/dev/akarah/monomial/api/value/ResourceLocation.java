package dev.akarah.monomial.api.value;

public interface ResourceLocation {
    String namespace();
    String path();

    static ResourceLocation fromString(String string) {
        if(string.contains(":")) {
            var split = string.split(":");
            return new WeakResourceLocation(split[0], split[1]);
        }
        return new WeakResourceLocation("minecraft", string);
    }
}
