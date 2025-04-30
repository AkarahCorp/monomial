package dev.akarah.monomial.api.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.jar.JarFile;

public class PluginLoader {
    static PluginLoader LOADER = new PluginLoader();

    public HashMap<String, Plugin> plugins = new HashMap<>();

    private PluginLoader() {}

    public static PluginLoader getInstance() {
        return LOADER;
    }

    public void loadPlugins() {
        loadPluginsFrom(Paths.get("./plugins/"));
    }

    public void loadPluginsFrom(Path directory) {
        System.out.println("Loading all plugins from directory " + directory + "...");
        try(var stream = Files.walk(directory)) {
            stream.forEach(file -> {
                if (Files.isRegularFile(file) && file.getFileName().toString().endsWith(".jar")) {
                    try {
                        this.loadPlugin(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (IOException e) {
            if(!Files.exists(directory)) {
                try {
                    Files.createDirectories(directory);
                } catch (IOException ignored) {}
                return;
            }
            throw new RuntimeException(e);
        }
    }

    public void loadPlugin(Path jarFilePath) throws IOException {
        System.out.println("Loading plugin from " + jarFilePath + "...");
        try(var jar = new JarFile(jarFilePath.toFile())) {
            var pluginMeta = jar.getJarEntry("plugin.json");
            if(pluginMeta == null) {
                System.out.println("uh oh failed to load this plugin at " + jarFilePath + " ill improve error later asdf");
                return;
            }

            var in = jar.getInputStream(pluginMeta);
            var json = new Gson().fromJson(new String(in.readAllBytes()), JsonElement.class);
            var meta = PluginConfig.CODEC.decode(JsonOps.INSTANCE, json).getOrThrow().getFirst();

            try(var loader = new PluginClassLoader(jarFilePath, this.getClass().getClassLoader())) {
                try {
                    var clazz = loader.loadClass(meta.mainClass());
                    var pluginInstance = (Plugin) clazz.getConstructor().newInstance();

                    pluginInstance.startup();

                    this.plugins.put(meta.namespace(), pluginInstance);
                } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                         InstantiationException | ClassCastException exception) {
                    System.out.println("ruh roh clazz " + meta.mainClass() + " not found D:");
                    exception.printStackTrace();
                }
            }
        }
    }

    public void unloadPlugins() {
        for(var entry : this.plugins.entrySet()) {
            entry.getValue().shutdown();
        }
        this.plugins.clear();
    }
}
