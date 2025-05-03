package dev.akarah.monomial.api.entity;

import dev.akarah.monomial.api.component.DataComponentType;

import java.util.Optional;

public interface Entity {
    <T> void set(DataComponentType<T> type, T value);
    <T> Optional<T> get(DataComponentType<T> type);
}
