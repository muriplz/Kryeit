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
import net.neoforged.neoforge.common.NeoForge;

@Mixin(FilteringBehaviour.class)
public abstract class FilteringBehaviourMixin extends BlockEntityBehaviour {

	public FilteringBehaviourMixin(SmartBlockEntity be) {
		super(be);
	}

	@Inject(
			method = "onShortInteract",
			at = @At("HEAD"),
			cancellable = true)
	public void onShortInteract(Player player, InteractionHand hand, Direction side, BlockHitResult hitResult, CallbackInfo ci) {
		SmartBlockEntity be = this.blockEntity;
		BlockPos pos = be.getBlockPos();

		FilterInteractEvent event = NeoForge.EVENT_BUS.post(new FilterInteractEvent((ServerPlayer) player, pos, be.getBlockState()));
		if (event.isCanceled()) ci.cancel();
	}

}
