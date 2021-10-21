package carpetaddons.mixins;


import carpet.script.language.Sys;
import carpetaddons.CarpetAddonsSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.layer.type.SouthEastSamplingLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.function.Predicate;

@Mixin(ProjectileUtil.class)
public class ProjectileUtilMixin {

    private static long time = 0;
    private static ArrayList<Long> times = new ArrayList<>();


    @Inject(method = "getCollision", at = @At(value ="HEAD"))
    private static void start(Entity entity, Predicate<Entity> predicate, CallbackInfoReturnable<HitResult> cir){
        time = System.nanoTime();
    }

    @Inject(method = "getCollision", at = @At(value ="RETURN"))
    private static void end(Entity entity, Predicate<Entity> predicate, CallbackInfoReturnable<HitResult> cir){
        times.add(System.nanoTime()-time);
        System.out.println(System.nanoTime() - time + " pos:" + entity.getPos());
        if(cir.getReturnValue().getType() != HitResult.Type.MISS){
            System.out.println((int)times.stream().mapToDouble(a -> a).average().getAsDouble());
            times.clear();
        }
    }

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
