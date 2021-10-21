package carpetaddons.mixins;

import carpetaddons.CarpetAddonsSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Redirect(
            method = "checkEntityChunkPos",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/entity/Entity.teleportRequested()Z"
            )
    )
    private boolean conditionAdder(Entity entity) {
        return CarpetAddonsSettings.keepEnderPearlsTicked ?
                (entity.teleportRequested() || (entity instanceof EnderPearlEntity) ||(entity instanceof SnowballEntity)) :
                entity.teleportRequested();
    }

    @Redirect(
            method = "tickEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/ServerChunkManager;shouldTickEntity(Lnet/minecraft/entity/Entity;)Z"
            )
    )
    public boolean onShouldTickEntity(ServerChunkManager serverChunkManager, Entity entity) {
        if (CarpetAddonsSettings.keepEnderPearlsTicked && (entity instanceof EnderPearlEntity ||entity instanceof SnowballEntity))
            return true;
        return serverChunkManager.shouldTickEntity(entity);
    }
}
