package cx.rain.mc.fabric.raincommons.event.args.player;

import cx.rain.mc.fabric.raincommons.event.args.EventArgs;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerJoinEvent extends EventArgs {
    private ServerPlayerEntity player;
    private ClientConnection connection;
    private MinecraftServer server;

    public PlayerJoinEvent(ServerPlayerEntity p, ClientConnection c, MinecraftServer s) {
        player = p;
        connection = c;
        server = s;
    }

    public ServerPlayerEntity getPlayer() {
        return player;
    }

    public MinecraftServer getServer() {
        return server;
    }

    public ClientConnection getConnection() {
        return connection;
    }
}
