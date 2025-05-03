package dev.akarah.monomial.api.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventHandler<T extends EventData<T>> {
    List<Consumer<T>> handlerList = new ArrayList<>();

    public static <T extends EventData<T>> EventHandler<T> create() {
        return new EventHandler<T>();
    }

    public void submit(Consumer<T> event) {
        this.handlerList.add(event);
    }

    public void fire(T event) {
        for(var handler : this.handlerList) {
            handler.accept(event);
        }
    }
}
