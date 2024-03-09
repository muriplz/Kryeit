package com.kryeit.kryeit.mixin.create;


import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.simibubi.create.foundation.block.IBE;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(value = IBE.class, remap = false)
public interface ClipboardBlockMixin {

	@Inject(method = "onBlockEntityUse", at = @At("HEAD"), cancellable = true)
	private <T extends BlockEntity> void onBlockEntityUse(BlockView world, BlockPos pos, Function<T, ActionResult> action, CallbackInfoReturnable<ActionResult> cir) {
		// Your injection code here
		cir.cancel();
		cir.setReturnValue(ActionResult.FAIL);
	}
}
