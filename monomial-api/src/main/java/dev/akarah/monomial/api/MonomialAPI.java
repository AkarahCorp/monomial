package dev.akarah.monomial.api;


import dev.akarah.monomial.api.server.MinecraftServer;

public class MonomialAPI {
    public static MinecraftServer minecraftServer;

    public static void setMinecraftServer(MinecraftServer server) {
        minecraftServer = server;
    }
}