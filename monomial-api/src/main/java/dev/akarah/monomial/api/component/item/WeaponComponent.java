package dev.akarah.monomial.api.component.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record WeaponComponent(
        int itemDamagePerAttack,
        float disableBlockingForSeconds
) {
    public static Codec<WeaponComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("item_damage_per_attack").forGetter(WeaponComponent::itemDamagePerAttack),
            Codec.FLOAT.fieldOf("disable_blocking_for_seconds").forGetter(WeaponComponent::disableBlockingForSeconds)
    ).apply(instance, WeaponComponent::new));
}
