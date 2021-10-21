package carpetaddons.mixins;


import carpetaddons.CarpetAddonsSettings;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ProjectileUtil.class)
public class ProjectileUtilMixin {

    @Redirect(method = "getCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;"))
    private static Vec3d changeRaycastLength(Vec3d vec3d, Vec3d vec){
        if(CarpetAddonsSettings.projectileRaycastLength > 0 && vec.length() > CarpetAddonsSettings.projectileRaycastLength){
            vec = vec.normalize();
            vec = vec.multiply(CarpetAddonsSettings.projectileRaycastLength);
            return vec3d.add(vec);
        }
        return vec3d.add(vec);
    }
}