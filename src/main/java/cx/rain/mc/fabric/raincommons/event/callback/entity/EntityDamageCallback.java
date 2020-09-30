package cx.rain.mc.fabric.raincommons.event.callback.entity;

import cx.rain.mc.fabric.raincommons.event.entity.EntityDamageEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface EntityDamageCallback {
    Event<EntityDamageCallback> EVENT =
            EventFactory.createArrayBacked(EntityDamageCallback.class,
                    listeners -> (event) -> {
                        for (EntityDamageCallback c : listeners) {
                            if (c.accept(event) == ActionResult.FAIL) {
                                return ActionResult.FAIL;
                            }
                        }
                        return ActionResult.PASS;
                    });

    ActionResult accept(EntityDamageEvent event);
}
