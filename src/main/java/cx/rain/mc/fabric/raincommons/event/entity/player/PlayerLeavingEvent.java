package cx.rain.mc.fabric.raincommons.event.entity.player;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerLeavingEvent {
    public MinecraftServer server;
    public ServerPlayerEntity player;

    public PlayerLeavingEvent(MinecraftServer serverIn, ServerPlayerEntity playerIn) {
        server = serverIn;
        player = playerIn;
    }
}
