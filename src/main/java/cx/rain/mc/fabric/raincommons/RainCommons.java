package cx.rain.mc.fabric.raincommons;

import cx.rain.mc.fabric.raincommons.event.callback.entity.player.PlayerChatCallback;
import cx.rain.mc.fabric.raincommons.event.callback.entity.player.PlayerJoinedCallback;
import cx.rain.mc.fabric.raincommons.event.callback.entity.player.PlayerLeavingCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RainCommons implements ModInitializer {
    public static final String Version = "1.0.1";

    private final boolean IsDev = false;

    private final Logger log = LogManager.getLogger("RainCommons");

    @Override
    public void onInitialize() {
        log.info("Working!");

        if (IsDev) {
            PlayerJoinedCallback.EVENT.register(event -> {
                log.info("[Test] Player " + event.player.getName().asString() + " joined server.");
                return ActionResult.PASS;
            });

            PlayerLeavingCallback.EVENT.register(event -> {
                log.info("[Test] Player " + event.player.getName().asString() + " is leaving.");
                return ActionResult.PASS;
            });

            PlayerChatCallback.EVENT.register(event -> {
                log.info("[Test] Player " + event.player.getName().asString() + ": " + event.text.asString());
                return TypedActionResult.pass(event.text);
            });
        }
    }
}
