package cx.rain.mc.fabric.raincommons.event.callback.entity.player;

import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerJoinedEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface PlayerJoinedCallback {
    Event<PlayerJoinedCallback> EVENT =
            EventFactory.createArrayBacked(PlayerJoinedCallback.class,
                    listeners -> (event) -> {
                        for (PlayerJoinedCallback c : listeners) {
                            if (c.accept(event) == ActionResult.FAIL) {
                                return ActionResult.FAIL;
                            }
                        }
                        return ActionResult.PASS;
                    });

    ActionResult accept(PlayerJoinedEvent event);
}
