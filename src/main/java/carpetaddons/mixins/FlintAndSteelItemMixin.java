package carpetaddons.mixins;

import carpetaddons.CarpetAddonsSettings;
import net.minecraft.block.BlockState;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FlintAndSteelItem.class)
public class FlintAndSteelItemMixin {

    @Redirect(method = "canIgnite", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;canPlaceAt(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z"))
    private static boolean canPlaceAt(BlockState blockState, WorldView world, BlockPos pos){
        if(CarpetAddonsSettings.oldFlintAndSteelBehavior)
            return true;
        return blockState.canPlaceAt(world,pos);
    }
}
