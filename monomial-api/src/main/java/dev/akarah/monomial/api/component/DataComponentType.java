package dev.akarah.monomial.api.component;

import com.mojang.serialization.Codec;
import dev.akarah.monomial.api.value.ResourceLocation;

public interface DataComponentType<T> {
    static <T> DataComponentType<T> create(Codec<T> codec) {
        return () -> codec;
    }

    Codec<T> codec();
}
