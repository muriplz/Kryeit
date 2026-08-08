package com.kryeit.kryeit.mixin.create;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.TrainRelocationEvent;
import com.simibubi.create.Create;
import com.simibubi.create.content.trains.entity.Train;
import com.simibubi.create.content.trains.entity.TrainRelocationPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

// Create 6 (NeoForge 1.21.1) packet handler is handle(ServerPlayer) -> void.
@Mixin(value = TrainRelocationPacket.class, remap = false)
public class TrainRelocationPacketMixin {

	@Shadow
	UUID trainId;

	@Shadow
	BlockPos pos;

	@Inject(method = "handle", at = @At("HEAD"), cancellable = true)
	private void kryeit$onHandle(ServerPlayer player, CallbackInfo ci) {
		if (player == null)
			return;

		Train train = Create.RAILWAYS.trains.get(trainId);
		if (train == null)
			return;

		var carriage = train.carriages.stream().findFirst().orElse(null);
		if (carriage == null)
			return;

		var entity = carriage.anyAvailableEntity();
		if (entity == null)
			return;

		BlockPos from = entity.blockPosition();
		BlockPos to = pos;

		if (!TrainRelocationEvent.EVENT.invoker().onTrainRelocation(player, train, from, to)) {
			ci.cancel();
		}
	}
}
