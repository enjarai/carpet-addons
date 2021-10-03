package carpetaddons.mixins;

import carpetaddons.CarpetAddonsSettings;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {

    @Inject(method = "canPlaceAt",at = @At(value = "HEAD"), cancellable = true)
    private static void canPlaceAt(World world, BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir){
        if(CarpetAddonsSettings.oldFlintAndSteelBehavior && world.isAir(pos)) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
  
}
