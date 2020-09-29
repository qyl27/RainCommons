package cx.rain.mc.fabric.raincommons.event.callback.entity;

import cx.rain.mc.fabric.raincommons.event.entity.EntityMoveEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface EntityMoveCallback {
    Event<EntityMoveCallback> EVENT =
            EventFactory.createArrayBacked(EntityMoveCallback.class,
                    listeners -> (event) -> {
                        for (EntityMoveCallback c : listeners) {
                            if (c.accept(event) == ActionResult.FAIL) {
                                return ActionResult.FAIL;
                            }
                        }
                        return ActionResult.PASS;
                    });

    ActionResult accept(EntityMoveEvent event);
}
