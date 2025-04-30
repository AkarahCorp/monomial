package dev.akarah.monomial.api.registry;

import dev.akarah.monomial.api.value.TypedResourceLocation;

import java.util.Optional;

public class Registries {
    static WritableRegistry<Registry<?>> ROOT_REGISTRY = new MappedRegistry<>();

    @SuppressWarnings("unchecked")
    public static <T> Optional<Registry<T>> lookup(TypedResourceLocation<T> resourceLocation) {
        return ROOT_REGISTRY.get(resourceLocation).map(x -> (Registry<T>) x);
    }

    public static <T> TypedResourceLocation<T> register(TypedResourceLocation<T> resourceLocation, Registry<T> registry) {
        ROOT_REGISTRY.register(resourceLocation, registry);
        return resourceLocation;
    }
}
