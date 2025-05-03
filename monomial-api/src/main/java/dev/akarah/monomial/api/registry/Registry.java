package dev.akarah.monomial.api.registry;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
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

    default Codec<T> codec() {
        var rg = this;
        return new Codec<>() {
            @Override
            public <T1> DataResult<Pair<T, T1>> decode(DynamicOps<T1> ops, T1 input) {
                var resourceLocation = ResourceLocation.CODEC.decode(ops, input).getOrThrow();
                var entry = rg.get(resourceLocation.getFirst()).orElseThrow();
                return DataResult.success(Pair.of(entry, resourceLocation.getSecond()));
            }

            @Override
            public <T1> DataResult<T1> encode(T input, DynamicOps<T1> ops, T1 prefix) {
                var resourceLocation = rg.getKeyOrThrow(input);
                return ResourceLocation.CODEC.encodeStart(ops, resourceLocation);
            }
        };
    }
}
