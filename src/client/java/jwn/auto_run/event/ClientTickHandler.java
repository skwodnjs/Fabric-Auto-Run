package jwn.auto_run.event;

import jwn.auto_run.IClientPlayerAccess;
import jwn.auto_run.mixin.client.KeyBindingAccessor;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class ClientTickHandler implements ClientTickEvents.EndTick {
    @Override
    public void onEndTick(MinecraftClient minecraftClient) {
        autoRun(minecraftClient);
    }

    private void autoRun(MinecraftClient minecraftClient) {
        KeyBindingAccessor forwardKey = (KeyBindingAccessor) MinecraftClient.getInstance().options.forwardKey;
        KeyBindingAccessor backKey = (KeyBindingAccessor) MinecraftClient.getInstance().options.backKey;
        KeyBindingAccessor leftKey = (KeyBindingAccessor) MinecraftClient.getInstance().options.leftKey;
        KeyBindingAccessor rightKey = (KeyBindingAccessor) MinecraftClient.getInstance().options.rightKey;

        if (minecraftClient.player instanceof IClientPlayerAccess playerAccess) {
            if (playerAccess.isAutoRunMode()) {
                minecraftClient.options.forwardKey.setPressed(true);
                if (!minecraftClient.player.isSprinting()) {
                    minecraftClient.player.setSprinting(true);
                }

                long window = MinecraftClient.getInstance().getWindow().getHandle();
                if (GLFW.glfwGetKey(window, forwardKey.getBoundKey().getCode()) == GLFW.GLFW_PRESS ||
                        GLFW.glfwGetKey(window, backKey.getBoundKey().getCode()) == GLFW.GLFW_PRESS ||
                        GLFW.glfwGetKey(window, leftKey.getBoundKey().getCode()) == GLFW.GLFW_PRESS ||
                        GLFW.glfwGetKey(window, rightKey.getBoundKey().getCode()) == GLFW.GLFW_PRESS) {
                    playerAccess.autoRunModeToggle();
                }
            }
        }
    }
}
