package dev.akarah.monomial.fabric;

import dev.akarah.monomial.api.plugin.PluginLoader;
import net.fabricmc.api.ModInitializer;

import java.nio.file.Paths;

public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        PluginLoader.getInstance().loadPlugins();
    }
}
