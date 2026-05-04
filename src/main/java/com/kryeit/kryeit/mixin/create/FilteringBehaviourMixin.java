package com.kryeit.kryeit.mixin.create;

import net.minecraft.util.hit.BlockHitResult;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.FilterInteractEvent;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

@Mixin(FilteringBehaviour.class)
public abstract class FilteringBehaviourMixin extends BlockEntityBehaviour {

	public FilteringBehaviourMixin(SmartBlockEntity be) {
		super(be);
	}

	@Inject(
			method = "onShortInteract",
			at = @At("HEAD"),
			cancellable = true)
	public void onShortInteract(PlayerEntity player, Hand hand, Direction side, BlockHitResult hitResult, CallbackInfo ci) {
		SmartBlockEntity be = this.blockEntity;

		BlockPos pos = be.getPos();

		if (!FilterInteractEvent.EVENT.invoker().onFilterInteract((ServerPlayerEntity) player, pos)) {
			ci.cancel();
		}
	}

}
