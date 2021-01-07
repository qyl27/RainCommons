package cx.rain.mc.fabric.raincommons;

import cx.rain.mc.fabric.raincommons.event.callback.PlayerLeaveCallback;
import cx.rain.mc.fabric.raincommons.event.callback.PlayerLoginCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.ActionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RainCommons implements ModInitializer {
    public static final String VERSION = "1.0.1";
    public static final String MC_VERSION = "1.16.4";
    public static final String FULL_VERSION = MC_VERSION + "-" + VERSION;

    private final boolean IsDev = true;

    private final Logger log = LogManager.getLogger("RainCommons");

    @Override
    public void onInitialize() {
        log.info("Initializing!");

        if (IsDev) {
            PlayerLoginCallback.EVENT.register((profile, connection) -> {
                log.info("PlayerLogin: " + profile.getName() + " (" + profile.getId() + ") "
                        + connection.getAddress());
                return ActionResult.PASS;
            });

            PlayerLeaveCallback.EVENT.register((profile, connection, reason) -> {
                log.info("PlayerLeave: " + profile.getName() + " (" + profile.getId() + ") "
                        + connection.getAddress());
                log.info("With reason: " + reason.asString());
            });
        }
    }
}
