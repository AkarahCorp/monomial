package dev.akarah.monomial.api.server;

import dev.akarah.monomial.api.MonomialAPI;
import dev.akarah.monomial.api.registry.Registry;

public interface MinecraftServer {
    static MinecraftServer get() {
        return MonomialAPI.minecraftServer;
    }
}
