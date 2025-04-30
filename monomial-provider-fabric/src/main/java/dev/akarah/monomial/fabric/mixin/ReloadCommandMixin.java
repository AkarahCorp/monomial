package dev.akarah.monomial.fabric.mixin;

import dev.akarah.monomial.api.plugin.PluginLoader;
import net.minecraft.server.commands.ReloadCommand;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(ReloadCommand.class)
public class ReloadCommandMixin {
    @Inject(method = "discoverNewPacks", at = @At("HEAD"))
    private static void discoverNewPacks(PackRepository packRepository, WorldData worldData, Collection<String> collection, CallbackInfoReturnable<Collection<String>> cir) {
        PluginLoader.getInstance().unloadPlugins();
        PluginLoader.getInstance().loadPlugins();
    }
}
