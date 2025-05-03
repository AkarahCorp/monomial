package dev.akarah.monomial.api.event;

import dev.akarah.monomial.api.event.world.PlayerBreakBlockEvent;

public class Events {
    public static <E extends EventData<E>> void fire(E event) {
        event.getEventHandler().fire(event);
    }

    public static EventHandler<PlayerBreakBlockEvent> PLAYER_BREAK_BLOCK = EventHandler.create();
}
