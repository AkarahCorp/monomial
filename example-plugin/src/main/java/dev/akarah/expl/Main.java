package dev.akarah.expl;

import dev.akarah.monomial.api.plugin.Plugin;

public class Main implements Plugin {
    @Override
    public void startup() {
        System.out.println("starting!");
    }

    @Override
    public void shutdown() {
        System.out.println("ending!");
    }
}