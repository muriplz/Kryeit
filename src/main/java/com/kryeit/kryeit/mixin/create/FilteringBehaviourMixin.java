package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.FilterInteractEvent;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

@Mixin(value = FilteringBehaviour.class, remap = false)
public abstract class FilteringBehaviourMixin extends BlockEntityBehaviour {

	public FilteringBehaviourMixin(SmartBlockEntity be) {
		super(be);
	}

	// Create 6: onShortInteract(Player, InteractionHand, Direction, BlockHitResult) -> void
	@Inject(method = "onShortInteract", at = @At("HEAD"), cancellable = true)
	private void kryeit$onShortInteract(Player player, InteractionHand hand, Direction side, BlockHitResult hitResult, CallbackInfo ci) {
		BlockPos pos = this.blockEntity.getBlockPos();

		if (!(player instanceof ServerPlayer serverPlayer))
			return;

		if (!FilterInteractEvent.EVENT.invoker().onFilterInteract(serverPlayer, pos)) {
			ci.cancel();
		}
	}
}
