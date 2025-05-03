package dev.akarah.monomial.api.registry;

import dev.akarah.monomial.api.value.ResourceLocation;

public interface WritableRegistry<T> extends Registry<T> {
    <U extends T> U register(ResourceLocation location, U value);
}
