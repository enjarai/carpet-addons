package carpetaddons.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin_llamaDupe {

    @Redirect(method = "remove", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandler;close(Lnet/minecraft/entity/player/PlayerEntity;)V"))
    private void addLamaDupe(ScreenHandler screenHandler, PlayerEntity player){
        System.out.println("Test");
    }
}
