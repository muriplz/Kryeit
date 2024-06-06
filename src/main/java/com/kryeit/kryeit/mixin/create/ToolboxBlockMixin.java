package com.kryeit.kryeit.mixin.create;


import com.kryeit.kryeit.event.ToolboxEquipEvent;
import com.kryeit.kryeit.event.ToolboxPickupEvent;
import com.simibubi.create.content.equipment.toolbox.ToolboxBlock;

import net.minecraft.block.BlockState;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ToolboxBlock.class)
public class ToolboxBlockMixin {

	@Inject(
			method = "onBlockBreakStart",
			at = @At("HEAD"),
			cancellable = true)
	public void onBreak(BlockState state, World world, BlockPos pos, PlayerEntity player, CallbackInfo ci) {
		if (player == null)
			return;
		if(!ToolboxPickupEvent.EVENT.invoker().onToolboxPickup((ServerPlayerEntity) player, pos))
			ci.cancel();
	}
}
