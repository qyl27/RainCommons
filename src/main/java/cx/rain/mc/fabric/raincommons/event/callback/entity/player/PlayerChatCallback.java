package cx.rain.mc.fabric.raincommons.event.callback.entity.player;

import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerChatEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;

public interface PlayerChatCallback {
    Event<PlayerChatCallback> EVENT =
            EventFactory.createArrayBacked(PlayerChatCallback.class,
                    listeners -> (event) -> {
                        for (PlayerChatCallback c : listeners) {
                            if (c.accept(event).getResult() == ActionResult.FAIL) {
                                return TypedActionResult.fail(event.text);
                            }
                        }
                        return TypedActionResult.pass(event.text);
                    });

    TypedActionResult<Text> accept(PlayerChatEvent event);
}
