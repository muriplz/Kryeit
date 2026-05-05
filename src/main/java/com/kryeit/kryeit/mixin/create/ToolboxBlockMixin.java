package com.kryeit.kryeit.mixin.create;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.ToolboxPickupEvent;
import com.simibubi.create.content.equipment.toolbox.ToolboxBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(value = ToolboxBlock.class)
public class ToolboxBlockMixin {

	@Inject(
			method = "attack",
			at = @At("HEAD"),
			cancellable = true)
	public void onBreak(BlockState state, Level world, BlockPos pos, Player player, CallbackInfo ci) {
		if (player == null) return;

		ToolboxPickupEvent event = Main.MOD_BUS.post(new ToolboxPickupEvent((ServerPlayer) player, pos));
		if (event.isCanceled()) ci.cancel();
	}
}
