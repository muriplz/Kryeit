package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.simibubi.create.foundation.utility.BlockHelper;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(BlockHelper.class)
public class BlockHelperMixin {

	@Inject(
			method = "placeSchematicBlock",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/block/entity/BlockEntity;createFromNbt(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/block/entity/BlockEntity;",
					shift = At.Shift.AFTER
			),
			cancellable = true
	)
	private static void onPlaceSchematicBlock(World world, BlockState state, BlockPos target, ItemStack stack, NbtCompound data, CallbackInfo ci) {
		BlockEntity loaded = BlockEntity.createFromNbt(target, state, data);
		if (loaded == null) {
			ci.cancel();
		}
	}
}
