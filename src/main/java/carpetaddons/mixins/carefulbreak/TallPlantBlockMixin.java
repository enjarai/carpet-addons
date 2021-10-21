package carpetaddons.mixins.carefulbreak;

import carpetaddons.CarpetAddonsSettings;
import carpetaddons.utils.CarefulBreakUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TallPlantBlock.class)
public class TallPlantBlockMixin {

    @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/TallPlantBlock;dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V"))
    private void onDropStacks(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack itemStack){
        if (CarpetAddonsSettings.carefulBreak && entity instanceof PlayerEntity && entity.isInSneakingPose()) {
            DoubleBlockHalf doubleBlockHalf = state.get(TallPlantBlock.HALF);
            if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
                BlockPos blockPos = pos.down();
                BlockState blockState = world.getBlockState(blockPos);
                if (blockState.getBlock() == state.getBlock() && blockState.get(TallPlantBlock.HALF) == DoubleBlockHalf.LOWER) {
                    CarefulBreakUtils.placeItemInInventory(blockState,world,blockPos,blockEntity,entity,itemStack);
                    world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent((PlayerEntity) entity, 2001, blockPos, Block.getRawIdFromState(blockState));
                }
            }else{
                CarefulBreakUtils.placeItemInInventory(state,world,pos,blockEntity,entity,itemStack);
            }
        }else{
            Block.dropStacks(state,world,pos,blockEntity,entity,itemStack);
        }
    }
}
