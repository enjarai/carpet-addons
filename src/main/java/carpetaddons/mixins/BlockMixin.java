package carpetaddons.mixins;

import carpet.CarpetServer;
import carpetaddons.CarpetAddonsSettings;
import carpetaddons.utils.CarefulBreakUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import static net.minecraft.block.Block.*;

@Mixin(Block.class)
public abstract class BlockMixin implements ItemConvertible {

    @Redirect(method = "afterBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V"))
    private void onDropStacks(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack){
            if (CarpetAddonsSettings.carefulBreak && entity instanceof PlayerEntity && entity.isInSneakingPose()) {
                CarefulBreakUtils.placeItemInInventory(state,world,pos,blockEntity,entity,stack);
            }else{
                Block.dropStacks(state,world,pos,blockEntity,entity,stack);
            }
    }

    //carefulBreak PISTON_HEADS
    @Inject(method = "onBreak", at = @At("HEAD"))
    private void onBreak1(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        if(CarpetAddonsSettings.carefulBreak && player.isInSneakingPose()) {
            if (state.getBlock() == Blocks.PISTON_HEAD) {
                Direction direction = state.get(FacingBlock.FACING).getOpposite();
                pos = pos.offset(direction);
                BlockState blockState = world.getBlockState(pos);
                Block block = world.getBlockState(pos).getBlock();
                if (block == Blocks.PISTON || block == Blocks.STICKY_PISTON && blockState.get(PistonBlock.EXTENDED)) {
                    CarefulBreakUtils.placeItemInInventory(blockState, world, pos, null, player, player.getMainHandStack());
                    world.removeBlock(pos, false);
                }
            }//else if(state.getBlock() instanceof TallPlantBlock && state.get(TallPlantBlock.HALF) == DoubleBlockHalf.UPPER){
             //   BlockPos blockPos = pos.down();
             //   BlockState blockState = world.getBlockState(blockPos);
             //   if (blockState.getBlock() == state.getBlock() && blockState.get(TallPlantBlock.HALF) == DoubleBlockHalf.LOWER) {
             //       CarefulBreakUtils.placeItemInInventory(blockState, world, pos, null, player, player.getMainHandStack());
             //       world.removeBlock(pos,false);
             //   }
            //}
        }
    }
}
