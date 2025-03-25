package jwn.auto_run.mixin.client;

import jwn.auto_run.IClientPlayerAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
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
        MinecraftClient.getInstance().player.sendMessage(Text.literal("Auto Run Mode: " + autoRunMode), true);

        if (!this.autoRunMode) {
            MinecraftClient.getInstance().player.setSprinting(false);
            MinecraftClient.getInstance().options.forwardKey.setPressed(false);
        } else {
            MinecraftClient.getInstance().player.setSprinting(true);
        }
    }
}
