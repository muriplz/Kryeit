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
import net.neoforged.neoforge.common.NeoForge;

@Mixin(MountedStorageManager.class)
public class MountedStorageManagerMixin {

	@Inject(
			method = "handlePlayerStorageInteraction",
			at = @At("HEAD"),
			cancellable = true
	)
	private void testing(Contraption contraption, Player player, BlockPos localPos, CallbackInfoReturnable<Boolean> cir) {
		if (!(contraption.entity instanceof CarriageContraptionEntity cce))
			return;

		Train train = cce.getCarriage().train;
		BlockPos pos = localPos;
		pos = pos.offset(cce.blockPosition());

		TrainStorageInteractEvent event = NeoForge.EVENT_BUS.post(new TrainStorageInteractEvent((ServerPlayer) player, train, pos));
		if (event.isCanceled()) cir.setReturnValue(false);
	}
}
