package jwn.auto_run.event;

import jwn.auto_run.IClientPlayerAccess;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class ClientTickHandler implements ClientTickEvents.EndTick {

    @Override
    public void onEndTick(MinecraftClient minecraftClient) {
        if (minecraftClient.player instanceof IClientPlayerAccess playerAccess) {
            if (playerAccess.isAutoRunMode()) {
                minecraftClient.player.setSprinting(true);
                minecraftClient.options.forwardKey.setPressed(true);
            }
        }
    }
}
