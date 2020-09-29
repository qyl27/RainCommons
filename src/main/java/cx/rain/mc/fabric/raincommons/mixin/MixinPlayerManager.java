package cx.rain.mc.fabric.raincommons.mixin;

import cx.rain.mc.fabric.raincommons.event.callback.entity.player.PlayerJoinedCallback;
import cx.rain.mc.fabric.raincommons.event.callback.entity.player.PlayerLeavingCallback;
import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerJoinedEvent;
import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerLeavingEvent;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public abstract class MixinPlayerManager {
    @Shadow
    @Final
    private MinecraftServer server;

    @Inject(method = "onPlayerConnect", at = @At("HEAD"), cancellable = true)
    public void afterOnPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        PlayerJoinedCallback.EVENT.invoker().accept(new PlayerJoinedEvent(server, player, connection));
    }

    @Inject(method = "remove", at = @At("RETURN"))
    public void beforeRemove(ServerPlayerEntity player, CallbackInfo ci) {
        PlayerLeavingCallback.EVENT.invoker().accept(new PlayerLeavingEvent(server, player));
    }
}
