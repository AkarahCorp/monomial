package dev.akarah.monomial.api.component;

public interface DataComponentType<T> {
    static <T> DataComponentType<T> create() {
        return new DataComponentType<T>() {};
    }
}
