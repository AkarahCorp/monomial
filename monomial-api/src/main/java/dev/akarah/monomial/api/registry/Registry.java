package dev.akarah.monomial.api.registry;

import dev.akarah.monomial.api.value.ResourceLocation;

import java.util.Optional;
import java.util.Set;

public interface Registry<T> {
    Optional<T> get(ResourceLocation key);

    default T getOrThrow(ResourceLocation key) {
        return this.get(key).orElseThrow();
    }

    Optional<ResourceLocation> getKey(T value);

    default ResourceLocation getKeyOrThrow(T value) {
        return this.getKey(value).orElseThrow();
    }

    Set<ResourceLocation> keySet();
}
