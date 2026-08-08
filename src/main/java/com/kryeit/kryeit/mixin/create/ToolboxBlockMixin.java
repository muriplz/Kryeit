package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.ToolboxPickupEvent;
import com.simibubi.create.content.equipment.toolbox.ToolboxBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(ToolboxBlock.class)
public class ToolboxBlockMixin {

	// Yarn onBlockBreakStart -> Mojmap attack(BlockState, Level, BlockPos, Player)
	@Inject(method = "attack", at = @At("HEAD"), cancellable = true)
	private void kryeit$onAttack(BlockState state, Level world, BlockPos pos, Player player, CallbackInfo ci) {
		if (!(player instanceof ServerPlayer serverPlayer))
			return;
		if (!ToolboxPickupEvent.EVENT.invoker().onToolboxPickup(serverPlayer, pos))
			ci.cancel();
	}
}
