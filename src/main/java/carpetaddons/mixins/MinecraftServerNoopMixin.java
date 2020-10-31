package carpetaddons.mixins;

import carpetaddons.CarpetAddonsExtension;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerNoopMixin
{
    @Inject(method = "<init>", at = @At("RETURN"))
    private void loadMe(CallbackInfo ci) {
        CarpetAddonsExtension.noop();
    }
}
