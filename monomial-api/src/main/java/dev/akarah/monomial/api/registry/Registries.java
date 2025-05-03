package dev.akarah.monomial.api.registry;

import dev.akarah.monomial.api.value.ResourceKey;

import java.util.Optional;

public class Registries {
    static WritableRegistry<Registry<?>> ROOT_REGISTRY = new MappedRegistry<>();

    @SuppressWarnings("unchecked")
    public static <T> Optional<Registry<T>> lookup(ResourceKey<T> resourceLocation) {
        return ROOT_REGISTRY.get(resourceLocation.asResourceLocation()).map(x -> (Registry<T>) x);
    }

    public static <T> ResourceKey<T> register(ResourceKey<T> resourceLocation, Registry<T> registry) {
        ROOT_REGISTRY.register(resourceLocation.asResourceLocation(), registry);
        return resourceLocation;
    }
}
