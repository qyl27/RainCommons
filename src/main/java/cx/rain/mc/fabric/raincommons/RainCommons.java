package cx.rain.mc.fabric.raincommons;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RainCommons implements ModInitializer {
    public static final String Version = "1.0.1";

    private final boolean IsDev = true;

    private final Logger log = LogManager.getLogger("RainCommons");

    @Override
    public void onInitialize() {
        log.info("Initializing!");

        if (IsDev) {

        }
    }
}
