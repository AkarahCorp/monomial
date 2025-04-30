package dev.akarah.expl;

import dev.akarah.monomial.api.component.DataComponentMap;
import dev.akarah.monomial.api.component.DataComponents;
import dev.akarah.monomial.api.plugin.Plugin;
import dev.akarah.monomial.api.registry.MappedRegistry;
import dev.akarah.monomial.api.value.ResourceLocation;

public class Main implements Plugin {
    @Override
    public void startup() {
        var map = DataComponentMap.builder()
                .set(DataComponents.TEST_COMPONENT, "hi")
                .build();

        System.out.println(map);

        var rg = new MappedRegistry<>();
        var rl = ResourceLocation.fromString("minecraft:hello");
        rg.register(rl, "abc");
        System.out.println(rg);
        System.out.println(rg.get(rl) + " " + rl);
    }

    @Override
    public void shutdown() {
        System.out.println("ending!");
    }
}