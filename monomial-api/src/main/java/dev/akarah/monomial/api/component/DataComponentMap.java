package dev.akarah.monomial.api.component;

import com.google.common.collect.Sets;
import com.mojang.serialization.*;
import dev.akarah.monomial.api.registry.Registries;
import dev.akarah.monomial.api.value.ResourceLocation;
import it.unimi.dsi.fastutil.objects.*;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface DataComponentMap {
    <T> Optional<T> get(DataComponentType<T> componentType);
    Set<DataComponentType<Object>> keySet();

    MapCodec<DataComponentMap> MAP_CODEC = new MapCodec<>() {
        @Override
        public <T> RecordBuilder<T> encode(DataComponentMap input, DynamicOps<T> ops, RecordBuilder<T> prefix) {
            for(var key : input.keySet()) {
                var resourceLocationEncoded = ResourceLocation.CODEC.encodeStart(
                        ops,
                        Registries.DATA_COMPONENT_TYPE.getKeyOrThrow(key)
                );
                var valueEncoded = key.codec().encodeStart(
                        ops,
                        input.get(key).orElseThrow()
                );
                prefix.add(resourceLocationEncoded, valueEncoded);
            }
            return prefix;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> DataResult<DataComponentMap> decode(DynamicOps<T> ops, MapLike<T> input) {
            var map = DataComponentMap.builder();
            for(var entry : input.entries().toList()) {
                var resourceLocation = ResourceLocation.CODEC.decode(ops, entry.getFirst()).getOrThrow().getFirst();
                var dataComponentType = Registries.DATA_COMPONENT_TYPE.getOrThrow(resourceLocation);
                var dataComponent = dataComponentType.codec().decode(ops, entry.getSecond()).getOrThrow().getFirst();
                map.setUnsafe((DataComponentType<Object>) dataComponentType, dataComponent);
            }
            return DataResult.success(map.build());
        }

        @Override
        public <T> Stream<T> keys(DynamicOps<T> ops) {
            return Stream.empty();
        }
    };

    Codec<DataComponentMap> CODEC = MAP_CODEC.codec();

    DataComponentMap EMPTY = new DataComponentMap() {
        @Override
        public <T> Optional<T> get(DataComponentType<T> componentType) {
            return Optional.empty();
        }

        @Override
        public Set<DataComponentType<Object>> keySet() {
            return Set.of();
        }
    };

    default DataComponentMap composite(DataComponentMap addendum) {
        var base = this;
        return new DataComponentMap() {
            @Override
            public <T> Optional<T> get(DataComponentType<T> componentType) {
                return base.get(componentType).or(() -> addendum.get(componentType));
            }

            @Override
            public Set<DataComponentType<Object>> keySet() {
                return Sets.union(base.keySet(), addendum.keySet());
            }
        };
    }

    default DataComponentMap filter(Predicate<DataComponentType<?>> predicate) {
        var map = this;
        return new DataComponentMap() {
            @Override
            public <T> Optional<T> get(DataComponentType<T> componentType) {
                return map.get(componentType).filter(x -> predicate.test(componentType));
            }

            @Override
            public Set<DataComponentType<Object>> keySet() {
                return Sets.filter(map.keySet(), predicate::test);
            }
        };
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder {
        Reference2ObjectMap<DataComponentType<Object>, Object> map = new Reference2ObjectArrayMap<>();

        Builder() {}

        @SuppressWarnings("unchecked")
        public <T> Builder set(DataComponentType<T> componentType, T value) {
            if(value == null) {
                this.map.remove(componentType);
            } else {
                this.map.put((DataComponentType<Object>) componentType, value);
            }
            return this;
        }

        public void setUnsafe(DataComponentType<Object> componentType, Object value) {
            if(value == null) {
                this.map.remove(componentType);
            } else {
                this.map.put(componentType, value);
            }
        }

        public <T> Builder remove(DataComponentType<T> componentType) {
            this.map.remove(componentType);
            return this;
        }

         public DataComponentMap build() {
            return new SimpleMap(this.map);
        }
    }

    record SimpleMap(Reference2ObjectMap<DataComponentType<Object>, Object> map) implements DataComponentMap {
        @Override
        @SuppressWarnings("unchecked")
        public <T> Optional<T> get(DataComponentType<T> componentType) {
            return Optional.ofNullable(this.map.get(componentType)).map(x -> (T) x);
        }

        @Override
        public Set<DataComponentType<Object>> keySet() {
            return this.map.keySet();
        }
    }
}
