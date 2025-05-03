package dev.akarah.monomial.fabric;

import dev.akarah.monomial.api.event.CancelToken;
import dev.akarah.monomial.api.event.Events;
import dev.akarah.monomial.api.event.world.PlayerBreakBlockEvent;
import dev.akarah.monomial.api.plugin.PluginLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        PluginLoader.getInstance().loadPlugins();

        PlayerBlockBreakEvents.BEFORE.register((level, player, blockPos, blockState, blockEntity) -> {
            var token = CancelToken.notCancelled();
            Events.fire(new PlayerBreakBlockEvent(token));
            return !token.isCancelled();
        });
    }
}
