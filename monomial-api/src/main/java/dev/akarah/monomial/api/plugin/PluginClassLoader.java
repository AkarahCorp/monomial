package dev.akarah.monomial.api.plugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

class PluginClassLoader extends URLClassLoader {
    public PluginClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public PluginClassLoader(Path path, ClassLoader parent) throws MalformedURLException {
        super(new URL[]{path.toUri().toURL()}, parent);
    }
}
