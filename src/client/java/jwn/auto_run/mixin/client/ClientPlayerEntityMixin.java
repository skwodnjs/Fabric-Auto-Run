package jwn.auto_run.mixin.client;

import jwn.auto_run.IClientPlayerAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin implements IClientPlayerAccess {
    @Unique
    private boolean autoRunMode = false;

    @Override
    public boolean isAutoRunMode() {
        return autoRunMode;
    }

    @Override
    public void autoRunModeToggle() {
        this.autoRunMode = !this.autoRunMode;
        if (!this.autoRunMode) {
            stop();
        }

    }

    private void stop() {
        MinecraftClient.getInstance().player.setSprinting(false);
        MinecraftClient.getInstance().options.forwardKey.setPressed(false);
    }
}
