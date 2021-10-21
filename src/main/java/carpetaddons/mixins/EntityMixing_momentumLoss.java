package carpetaddons.mixins;


import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Entity.class)
public class EntityMixing_momentumLoss {

    @ModifyConstant(method = "fromTag", constant = @Constant(doubleValue = 10.0D), require = 3)
    private double dontYeetItsSpeed(double value)
    {
            return Double.MAX_VALUE;
    }
}
