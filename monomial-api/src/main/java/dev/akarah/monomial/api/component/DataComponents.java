package dev.akarah.monomial.api.component;

import com.mojang.serialization.Codec;
import dev.akarah.monomial.api.component.item.WeaponComponent;
import dev.akarah.monomial.api.registry.Registries;
import dev.akarah.monomial.api.value.ResourceLocation;

public class DataComponents {
    public static DataComponentType<Integer> DAMAGE = Registries.DATA_COMPONENT_TYPE.register(
            ResourceLocation.create("minecraft", "damage"),
            DataComponentType.create(Codec.INT)
    );

    public static DataComponentType<Integer> MAX_DAMAGE = Registries.DATA_COMPONENT_TYPE.register(
            ResourceLocation.create("minecraft", "max_damage"),
            DataComponentType.create(Codec.INT)
    );

    public static DataComponentType<WeaponComponent> WEAPON = Registries.DATA_COMPONENT_TYPE.register(
            ResourceLocation.create("minecraft", "weapon"),
            DataComponentType.create(WeaponComponent.CODEC)
    );
}
