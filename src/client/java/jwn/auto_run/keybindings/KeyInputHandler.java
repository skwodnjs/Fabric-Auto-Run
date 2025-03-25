package jwn.auto_run.keybindings;

import jwn.auto_run.AutoRunClient;
import jwn.auto_run.IClientPlayerAccess;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_KEY_BINDINGS = "key.category." + AutoRunClient.MOD_ID + ".key_bindings";
    public static final String KEY_AUTO_RUN = "key." + AutoRunClient.MOD_ID + ".auto_run";

    public static KeyBinding AutoRunKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (AutoRunKey.wasPressed()) {
                if (client.player != null) {
                    if (client.player instanceof IClientPlayerAccess playerAccess) {
                        playerAccess.autoRunModeToggle();
                    }
                }
            }
        });
    }

    public static void register() {
        AutoRunKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_AUTO_RUN,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_EQUAL,
                KEY_CATEGORY_KEY_BINDINGS
        ));

        registerKeyInputs();
    }
}
