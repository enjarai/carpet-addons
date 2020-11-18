package carpetaddons.mixins;

import carpetaddons.CarpetAddonsSettings;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.HoeItem;
import net.minecraft.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HoeItem.class)
public class HoeItemMixin {

    @Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isAir()Z"))
    private boolean fixUnderwaterHoe(BlockState state){
        if(CarpetAddonsSettings.fixUnderwaterHoe)
            return state.isAir() || state.getFluidState().matches(FluidTags.WATER);
        else
            return state.isAir();
    }
}
