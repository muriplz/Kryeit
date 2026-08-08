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

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

@Mixin(value = MountedStorageManager.class, remap = false)
public class MountedStorageManagerMixin {

	@Inject(method = "handlePlayerStorageInteraction", at = @At("HEAD"), cancellable = true)
	private void kryeit$onStorageInteract(Contraption contraption, Player player, BlockPos localPos, CallbackInfoReturnable<Boolean> cir) {
		if (!(contraption.entity instanceof CarriageContraptionEntity cce))
			return;

		if (!(player instanceof ServerPlayer serverPlayer))
			return;

		Train train = cce.getCarriage().train;
		BlockPos pos = localPos.offset(cce.blockPosition());

		if (!TrainStorageInteractEvent.EVENT.invoker().onTrainStorageInteract(serverPlayer, train, pos)) {
			cir.setReturnValue(false);
		}
	}
}
