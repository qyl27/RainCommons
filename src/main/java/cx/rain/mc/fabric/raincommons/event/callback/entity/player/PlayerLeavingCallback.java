package cx.rain.mc.fabric.raincommons.event.callback.entity.player;

import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerLeavingEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface PlayerLeavingCallback {
    Event<PlayerLeavingCallback> EVENT =
            EventFactory.createArrayBacked(PlayerLeavingCallback.class,
                    listeners -> (event) -> {
                        for (PlayerLeavingCallback c : listeners) {
                            ActionResult result = c.accept(event);
                            if (result != ActionResult.FAIL) {
                                return result;
                            }
                        }
                        return ActionResult.PASS;
                    });

    ActionResult accept(PlayerLeavingEvent event);
}
