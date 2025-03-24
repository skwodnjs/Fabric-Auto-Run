package jwn.auto_run;

import jwn.auto_run.keybindings.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoRunClient implements ClientModInitializer {
	public static final String MOD_ID = "auto_run";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		KeyInputHandler.register();

		LOGGER.info("Hello Fabric world!");
	}
}