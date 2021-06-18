package carpetaddons.mixins;

import carpetaddons.CarpetAddonsSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PotionEntity.class)
public class PotionEntityMixin {
    @Redirect(method = "applySplashPotion", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z"))
    private boolean onAddStatusEffect(LivingEntity livingEntity, StatusEffectInstance effect, @Nullable Entity source){
        if(CarpetAddonsSettings.stackingSplashPotions) {
            StatusEffectInstance oldEffect = livingEntity.getStatusEffect(effect.getEffectType());
            int oldDuration = 0;
            if (oldEffect != null && oldEffect.getAmplifier() == effect.getAmplifier())
                oldDuration = oldEffect.getDuration();
            StatusEffectInstance newEffect = new StatusEffectInstance(effect.getEffectType(), effect.getDuration() + oldDuration, effect.getAmplifier());
            return livingEntity.addStatusEffect(newEffect,source);
        }
        return livingEntity.addStatusEffect(effect,source);
    }

}
