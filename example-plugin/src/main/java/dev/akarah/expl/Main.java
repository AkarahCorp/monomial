package dev.akarah.expl;

import dev.akarah.monomial.api.component.DataComponentMap;
import dev.akarah.monomial.api.component.DataComponents;
import dev.akarah.monomial.api.plugin.Plugin;

public class Main implements Plugin {
    @Override
    public void startup() {
        var map = DataComponentMap.builder()
                .set(DataComponents.TEST_COMPONENT, "hi")
                .build();

        System.out.println(map);
    }

    @Override
    public void shutdown() {
        System.out.println("ending!");
    }
}