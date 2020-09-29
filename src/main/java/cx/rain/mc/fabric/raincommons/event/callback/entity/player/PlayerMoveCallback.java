package cx.rain.mc.fabric.raincommons.event.callback.entity.player;

import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerMoveEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface PlayerMoveCallback {
    Event<PlayerMoveCallback> EVENT =
            EventFactory.createArrayBacked(PlayerMoveCallback.class,
                    listeners -> (event) -> {
                        for (PlayerMoveCallback c : listeners) {
                            if (c.accept(event) == ActionResult.FAIL) {
                                return ActionResult.FAIL;
                            }
                        }
                        return ActionResult.PASS;
                    });

    ActionResult accept(PlayerMoveEvent event);
}
