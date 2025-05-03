package dev.akarah.expl;

import com.mojang.serialization.JsonOps;
import dev.akarah.monomial.api.component.DataComponentMap;
import dev.akarah.monomial.api.component.DataComponents;
import dev.akarah.monomial.api.event.Events;
import dev.akarah.monomial.api.plugin.Plugin;
import dev.akarah.monomial.api.registry.MappedRegistry;
import dev.akarah.monomial.api.registry.Registry;
import dev.akarah.monomial.api.value.ResourceLocation;

public class Main implements Plugin {
    @Override
    public void startup() {
        Events.PLAYER_BREAK_BLOCK.submit(event -> {
            var map = DataComponentMap.builder()
                    .set(DataComponents.DAMAGE, 15)
                    .set(DataComponents.MAX_DAMAGE, 30)
                    .build();

            var encodedMap = DataComponentMap.CODEC.encodeStart(JsonOps.INSTANCE, map);
            System.out.println(encodedMap);

            var registry = MappedRegistry.<Integer>create();
            registry.register(ResourceLocation.fromString("minecraft:ten"), 10);
            registry.register(ResourceLocation.fromString("minecraft:twenty"), 20);
            registry.register(ResourceLocation.fromString("minecraft:thirty"), 30);

            var encodedRegistry = registry.codec().encodeStart(JsonOps.INSTANCE, 10);
            System.out.println(encodedRegistry);
        });
    }

    @Override
    public void shutdown() {
    }
}