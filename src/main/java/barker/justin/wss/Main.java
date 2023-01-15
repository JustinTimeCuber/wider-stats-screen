package barker.justin.wss;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("wider-stats-screen");

	@Override
	public void onInitialize() {
		LOGGER.info("Wider Stats Screen loaded.");
	}
}
