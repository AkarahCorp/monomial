package dev.akarah.monomial.fabric.impls;

import dev.akarah.monomial.api.component.DataComponentType;
import dev.akarah.monomial.api.entity.Entity;

import java.util.Optional;

public class EntityImpl implements Entity {
    net.minecraft.world.entity.Entity backingEntity;

    public EntityImpl(net.minecraft.world.entity.Entity backingEntity) {
        this.backingEntity = backingEntity;
    }

    @Override
    public <T> void set(DataComponentType<T> type, T value) {

    }

    @Override
    public <T> Optional<T> get(DataComponentType<T> type) {
        return Optional.empty();
    }
}
