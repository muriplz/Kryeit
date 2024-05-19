package com.kryeit.kryeit.mixin.create;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kryeit.kryeit.event.TrainAssemblyModeEvent;
import com.simibubi.create.content.trains.station.GlobalStation;
import com.simibubi.create.content.trains.station.StationBlockEntity;

import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(value = StationBlockEntity.class, remap = false)
public abstract class StationBlockEntityMixin {

	@Shadow
	@Nullable
	public abstract GlobalStation getStation();

	@Inject(
			method = "enterAssemblyMode",
			at = @At("HEAD"), cancellable = true
	)
	public void onAssemblyMode(ServerPlayerEntity sender, CallbackInfoReturnable<Boolean> cir) {
		GlobalStation station = getStation();

		if (station == null) {
			return;
		}

		if (!TrainAssemblyModeEvent.EVENT.invoker().onTrainAssembly(sender, station.getPresentTrain(), station.getBlockEntityPos())) {
			cir.setReturnValue(false);
		}
	}
}
