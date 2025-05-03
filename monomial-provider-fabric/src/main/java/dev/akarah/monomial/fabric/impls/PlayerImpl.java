package dev.akarah.monomial.fabric.impls;

import dev.akarah.monomial.api.component.DataComponentType;
import dev.akarah.monomial.api.entity.Player;

import java.util.Optional;

public class PlayerImpl implements Player {
    net.minecraft.world.entity.player.Player backingEntity;

    public PlayerImpl(net.minecraft.world.entity.player.Player backingEntity) {
        this.backingEntity = backingEntity;
    }

    @Override
    public <T> void set(DataComponentType<T> type, T value) {
        new EntityImpl(this.backingEntity).set(type, value);
    }

    @Override
    public <T> Optional<T> get(DataComponentType<T> type) {
        return new EntityImpl(this.backingEntity).get(type);
    }
}
