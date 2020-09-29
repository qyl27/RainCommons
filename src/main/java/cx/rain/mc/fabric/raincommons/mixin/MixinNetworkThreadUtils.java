package cx.rain.mc.fabric.raincommons.mixin;

import cx.rain.mc.fabric.raincommons.event.callback.entity.player.PlayerChatCallback;
import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerChatEvent;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.thread.ThreadExecutor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(NetworkThreadUtils.class)
public abstract class MixinNetworkThreadUtils {
    @Inject(method = "forceMainThread(Lnet/minecraft/network/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/util/thread/ThreadExecutor;)V",
    at = @At("RETURN"),cancellable = true)
    private static <T extends PacketListener> void afterForceMainThread(Packet<T> packet, T packetListener,
                                                                        ThreadExecutor<?> threadExecutor, CallbackInfo ci) {
        if(threadExecutor instanceof MinecraftServer) {
            List<ServerPlayerEntity> playerList=((MinecraftServer) threadExecutor).getPlayerManager().getPlayerList();
            if (packet instanceof ChatMessageC2SPacket) {
                for (ServerPlayerEntity player : playerList)
                    if (PlayerChatCallback.EVENT.invoker().accept(new PlayerChatEvent(player,
                            new LiteralText(((ChatMessageC2SPacket) packet).getChatMessage()))).getResult() ==
                            ActionResult.FAIL)
                        ci.cancel();
            }
        }
    }
}
