package cx.rain.mc.fabric.raincommons.event.callback.entity.player;

import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerInteractEntityEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface PlayerInteractEntityCallback {
    Event<PlayerInteractEntityCallback> EVENT =
            EventFactory.createArrayBacked(PlayerInteractEntityCallback.class,
                    listeners -> (event) -> {
                        for (PlayerInteractEntityCallback c : listeners) {
                            if (c.accept(event) == ActionResult.FAIL) {
                                return ActionResult.FAIL;
                            }
                        }
                        return ActionResult.PASS;
                    });

    ActionResult accept(PlayerInteractEntityEvent event);
}
