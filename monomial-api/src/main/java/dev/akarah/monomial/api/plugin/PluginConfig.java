package dev.akarah.monomial.api.plugin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record PluginConfig(
        String mainClass,
        String namespace
) {
    public static Codec<PluginConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("main_class").forGetter(PluginConfig::mainClass),
            Codec.STRING.fieldOf("namespace").forGetter(PluginConfig::namespace)
    ).apply(instance, PluginConfig::new));
}
