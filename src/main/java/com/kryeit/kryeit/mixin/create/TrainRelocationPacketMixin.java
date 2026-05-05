package com.kryeit.kryeit.mixin.create;

import java.util.UUID;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainRelocationEvent;
import com.simibubi.create.Create;
import com.simibubi.create.content.trains.entity.Train;
import com.simibubi.create.content.trains.entity.TrainRelocationPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

@Mixin(value = TrainRelocationPacket.class, remap = false)
public class TrainRelocationPacketMixin {
	@Final
	@Shadow
	private UUID trainId;
	@Final
	@Shadow
	private BlockPos pos;

	@Inject(method = "handle", remap = false, at = @At("HEAD"), cancellable = true)
	public void onHandle(ServerPlayer sender, CallbackInfo ci) {
		Train train = Create.RAILWAYS.trains.get(trainId);
		if (train == null) return;

		BlockPos from = train.carriages.stream().findFirst().get().anyAvailableEntity().blockPosition();
		BlockPos to = pos;

		TrainRelocationEvent event = Main.MOD_BUS.post(new TrainRelocationEvent(sender, train, from, to));
		if (event.isCanceled()) ci.cancel();
	}
}
