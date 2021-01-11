package cx.rain.mc.fabric.raincommons.mixin;

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
public class PlayerManagerMixin {
    @Final
    @Shadow
    private MinecraftServer server;

    @Inject(at = @At("HEAD"), method = "onPlayerConnect", cancellable = true)
    public void beforeLogin(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        //CommonsEvent.invoke(new PlayerJoinEvent(player, connection, server));
    }

    @Inject(at = @At("HEAD"), method = "remove", cancellable = true)
    public void beforeRemove(ServerPlayerEntity player, CallbackInfo ci) {
        //CommonsEvent.invoke(new PlayerJoinEvent(player, connection, server));
    }
}
