package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kryeit.kryeit.event.TrainStorageInteractEvent;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.MountedStorageManager;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

@Mixin(MountedStorageManager.class)
public class MountedStorageManagerMixin {

	@Inject(
			method = "handlePlayerStorageInteraction",
			at = @At("HEAD"),
			cancellable = true
	)
	private void testing(Contraption contraption, PlayerEntity player, BlockPos localPos, CallbackInfoReturnable<Boolean> cir) {
		if (!(contraption.entity instanceof CarriageContraptionEntity cce))
			return;

		Train train = cce.getCarriage().train;
		BlockPos pos = localPos;
		pos = pos.add(cce.getBlockPos());

		if (!TrainStorageInteractEvent.EVENT.invoker().onTrainStorageInteract((ServerPlayerEntity) player, train, pos)) {
			cir.setReturnValue(false);
		}
	}
}
