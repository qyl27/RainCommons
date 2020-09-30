package cx.rain.mc.fabric.raincommons.event.callback.entity.player;

import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerInteractAtEntityEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface PlayerInteractAtEntityCallback {
    Event<PlayerInteractAtEntityCallback> EVENT =
            EventFactory.createArrayBacked(PlayerInteractAtEntityCallback.class,
                    listeners -> (event) -> {
                        for (PlayerInteractAtEntityCallback c : listeners) {
                            if (c.accept(event) == ActionResult.FAIL) {
                                return ActionResult.FAIL;
                            }
                        }
                        return ActionResult.PASS;
                    });

    ActionResult accept(PlayerInteractAtEntityEvent event);
}
