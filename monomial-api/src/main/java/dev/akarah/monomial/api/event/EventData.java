package dev.akarah.monomial.api.event;

public interface EventData<S extends EventData<S>> {
    EventHandler<S> getEventHandler();
}
