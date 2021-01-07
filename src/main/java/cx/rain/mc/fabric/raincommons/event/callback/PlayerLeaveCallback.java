package cx.rain.mc.fabric.raincommons.event.callback;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;

public interface PlayerLeaveCallback {
    Event<PlayerLeaveCallback> EVENT = EventFactory.createArrayBacked(PlayerLeaveCallback.class,
            listeners -> (profile, connection, reason) -> {
                for (PlayerLeaveCallback callback : listeners) {
                    callback.accept(profile, connection, reason);
                }
            });

    void accept(GameProfile profile, ClientConnection connection, Text reason);
}
