package cx.rain.mc.fabric.raincommons.event.entity.player;

import cx.rain.mc.fabric.raincommons.event.EventBase;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerJoinedEvent extends EventBase {
    public MinecraftServer server;
    public ServerPlayerEntity player;
    public ClientConnection connection;

    public PlayerJoinedEvent(MinecraftServer serverIn, ServerPlayerEntity playerIn, ClientConnection connectionIn) {
        server = serverIn;
        player = playerIn;
        connection = connectionIn;
    }
}
