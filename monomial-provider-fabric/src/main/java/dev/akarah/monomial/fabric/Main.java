package dev.akarah.monomial.fabric;

import dev.akarah.monomial.api.MonomialAPI;
import dev.akarah.monomial.api.event.CancelToken;
import dev.akarah.monomial.api.event.Events;
import dev.akarah.monomial.api.event.world.PlayerBreakBlockEvent;
import dev.akarah.monomial.api.plugin.PluginLoader;
import dev.akarah.monomial.fabric.impls.PlayerImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        MonomialAPI.setMinecraftServer(new FabricMinecraftServer());
        PluginLoader.getInstance().loadPlugins();

        PlayerBlockBreakEvents.BEFORE.register((level, player, blockPos, blockState, blockEntity) -> {
            var token = CancelToken.notCancelled();
            var apiPlayer = new PlayerImpl(player);
            Events.fire(new PlayerBreakBlockEvent(apiPlayer, token));
            return !token.isCancelled();
        });
    }
}
