package dev.akarah.monomial.api.registry;

import dev.akarah.monomial.api.value.ResourceLocation;

public interface WritableRegistry<T> extends Registry<T> {
    Registry<T> register(ResourceLocation location, T value);
}
