package dev.akarah.monomial.api.component;

import it.unimi.dsi.fastutil.objects.*;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Set;

public interface DataComponentMap {
    <T> Optional<T> get(DataComponentType<T> componentType);
    Set<DataComponentType<?>> keySet();

    DataComponentMap EMPTY = new DataComponentMap() {
        @Override
        public <T> Optional<T> get(DataComponentType<T> componentType) {
            return Optional.empty();
        }

        @Override
        public Set<DataComponentType<?>> keySet() {
            return Set.of();
        }
    };

    static Builder builder() {
        return new Builder();
    }

    class Builder {
        Reference2ObjectMap<DataComponentType<?>, Object> map = new Reference2ObjectArrayMap<>();

        Builder() {}

        public <T> Builder set(DataComponentType<T> componentType, T value) {
            if(value == null) {
                this.map.remove(componentType);
            } else {
                this.map.put(componentType, value);
            }
            return this;
        }

        public <T> Builder remove(DataComponentType<T> componentType) {
            this.map.remove(componentType);
            return this;
        }

         public DataComponentMap build() {
            return new SimpleMap(this.map);
        }
    }

    record SimpleMap(Reference2ObjectMap<DataComponentType<?>, ?> map) implements DataComponentMap {
        @Override
        @SuppressWarnings("unchecked")
        public <T> Optional<T> get(DataComponentType<T> componentType) {
            return Optional.ofNullable(this.map.get(componentType)).map(x -> (T) x);
        }

        @Override
        public Set<DataComponentType<?>> keySet() {
            return this.map.keySet();
        }
    }
}
