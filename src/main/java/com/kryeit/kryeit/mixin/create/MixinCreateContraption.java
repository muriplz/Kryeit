package com.kryeit.kryeit.mixin.create;

import com.kryeit.kryeit.event.ContraptionMoveEvent;
import com.simibubi.create.content.contraptions.Contraption;

import net.minecraft.core.BlockPos;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Contraption.class, priority = 1000001)
public class MixinCreateContraption {

	@Shadow
	public BlockPos anchor;

	@Inject(method = "movementAllowed", at = @At("HEAD"), cancellable = true)
	public void onMovementAllowed(BlockState state, Level level, BlockPos pos, CallbackInfoReturnable<Boolean> cir){
		if(ContraptionMoveEvent.EVENT.invoker().onContraptionMove(level, (Contraption) (Object) this))
			cir.setReturnValue(false);
	}

//	@ModifyArg(method = "addBlocksToWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"))
//	public BlockPos onAddBlocksToWorld(BlockPos posToCapture){
//		ServerCore.CAPTURED_TARGET_POS = posToCapture;
//		return posToCapture;
//	}
//
//	@ModifyVariable(method = "addBlocksToWorld", name = "blockState", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/level/Level;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"))
//	public BlockState onAddBlocksToWorld(BlockState actual, Level level, StructureTransform structureTransform){
//		return ServerCore.replaceBlockFetchOnCreateModBreak(actual, level, anchor);
//	}
//
//	@Inject(method = "addBlocksToWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
//	public void preAddSuperGlueToWorld(Level level, StructureTransform structureTransform, CallbackInfo ci){
//		ServerCore.preCreateDisassembleSuperGlue(level, anchor);
//	}
//
//	@Inject(method = "addBlocksToWorld", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
//	public void postAddSuperGlueToWorld(Level level, StructureTransform structureTransform, CallbackInfo ci){
//		ServerCore.postCreateDisassembleSuperGlue();
//	}

}
