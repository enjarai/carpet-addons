package carpetaddons.mixins;

import carpetaddons.CarpetAddonsSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
public class PotionItemMixin {

    /**
     * @author whoImT
     */
    @Overwrite
    public int getMaxUseTime(ItemStack stack) {
        if(CarpetAddonsSettings.instantPotions)
            return 1;
        return 32;
    }

    @Redirect(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z"))
    private boolean onAddStatusEffect(LivingEntity livingEntity, StatusEffectInstance effect){
        if(CarpetAddonsSettings.stackingPotions) {
            StatusEffectInstance oldEffect = livingEntity.getStatusEffect(effect.getEffectType());
            int oldDuration = 0;
            if (oldEffect != null && oldEffect.getAmplifier() == effect.getAmplifier())
                oldDuration = oldEffect.getDuration();
            StatusEffectInstance newEffect = new StatusEffectInstance(effect.getEffectType(), effect.getDuration() + oldDuration, effect.getAmplifier());
            return livingEntity.addStatusEffect(newEffect);
        }
        return livingEntity.addStatusEffect(effect);
    }
}
