package dev.akarah.monomial.api.event.world;

import dev.akarah.monomial.api.entity.Player;
import dev.akarah.monomial.api.event.CancelToken;
import dev.akarah.monomial.api.event.EventData;
import dev.akarah.monomial.api.event.EventHandler;
import dev.akarah.monomial.api.event.Events;

public record PlayerBreakBlockEvent(Player player, CancelToken cancelToken) implements EventData<PlayerBreakBlockEvent>  {
    @Override
    public EventHandler<PlayerBreakBlockEvent> getEventHandler() {
        return Events.PLAYER_BREAK_BLOCK;
    }
}
