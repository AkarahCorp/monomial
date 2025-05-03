package dev.akarah.monomial.api.registry;

import com.mojang.serialization.Codec;
import dev.akarah.monomial.api.value.ResourceLocation;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class MappedRegistry<T> implements WritableRegistry<T> {
    Map<ResourceLocation, T> map = new Reference2ObjectArrayMap<>();
    Map<T, ResourceLocation> inverse = new Reference2ObjectArrayMap<>();

    private MappedRegistry() {
    }

    public static <T> MappedRegistry<T> create() {
        return new MappedRegistry<T>();
    }

    public <U extends T> U register(ResourceLocation location, U value) {
        this.map.put(location, value);
        this.inverse.put(value, location);
        return value;
    }

    @Override
    public Optional<T> get(ResourceLocation key) {
        return Optional.ofNullable(this.map.get(key));
    }

    @Override
    public Optional<ResourceLocation> getKey(T value) {
        return Optional.ofNullable(this.inverse.get(value));
    }

    @Override
    public Set<ResourceLocation> keySet() {
        return this.map.keySet();
    }
}
