package dev.akarah.expl;

import dev.akarah.monomial.api.event.Events;
import dev.akarah.monomial.api.plugin.Plugin;

public class Main implements Plugin {
    @Override
    public void startup() {
        Events.PLAYER_BREAK_BLOCK.submit(event -> {
            System.out.println("event: " + event);
        });
    }

    @Override
    public void shutdown() {
    }
}