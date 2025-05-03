package dev.akarah.monomial.api.registry;

import dev.akarah.monomial.api.component.DataComponentType;
import dev.akarah.monomial.api.value.ResourceKey;

import java.util.Optional;

public class Registries {
    static WritableRegistry<Registry<?>> ROOT_REGISTRY = MappedRegistry.create();

    @SuppressWarnings("unchecked")
    public static <T> Optional<Registry<T>> lookup(ResourceKey<T> resourceLocation) {
        return ROOT_REGISTRY.get(resourceLocation.asResourceLocation()).map(x -> (Registry<T>) x);
    }

    public static <T> ResourceKey<T> register(ResourceKey<T> resourceLocation, Registry<T> registry) {
        ROOT_REGISTRY.register(resourceLocation.asResourceLocation(), registry);
        return resourceLocation;
    }

    public static WritableRegistry<DataComponentType<?>> DATA_COMPONENT_TYPE = MappedRegistry.create();

    public static ResourceKey<DataComponentType<?>> DATA_COMPONENT_TYPE_PATH =
            Registries.register(ResourceKey.create("minecraft", "data_component_type"), DATA_COMPONENT_TYPE);
}
