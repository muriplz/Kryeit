package com.kryeit.kryeit.mixin.create;

import java.util.UUID;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kryeit.kryeit.MinecraftServerSupplier;
import com.kryeit.kryeit.event.TrainAssembleEvent;
import com.kryeit.kryeit.event.TrainDisassembleEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.station.GlobalStation;
import com.simibubi.create.content.trains.station.StationBlockEntity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.common.NeoForge;


@Mixin(value = StationBlockEntity.class, remap = false)
public abstract class StationBlockEntityMixin {
	@Shadow
	@Nullable
	public abstract GlobalStation getStation();

	@Inject(
			method = "updateName",
			at = @At("RETURN"), cancellable = true
	)
	public void onNameChange(String name, CallbackInfoReturnable<Boolean> cir) {
		GlobalStation station = getStation();
		if (station == null) return;

		ServerPlayer player = Utils.getClosestPlayer(station.getBlockEntityPos(), station.getBlockEntityDimension());
		if (player == null) return;

		TrainAssembleEvent event = NeoForge.EVENT_BUS.post(new TrainAssembleEvent(player, station.getPresentTrain(), station.getBlockEntityPos()));
		if (event.isCanceled()) cir.setReturnValue(false);
	}

	@Inject(
			method = "assemble",
			at = @At("RETURN"), cancellable = true
	)
	public void onAssemble(UUID playerUUID, CallbackInfo ci) {
		GlobalStation station = getStation();

		if (station == null) return;

		ServerLevel world = MinecraftServerSupplier.getServer().getLevel(station.getBlockEntityDimension());
		if (world == null) return;

		Entity entity = world.getEntity(playerUUID);
		if (!(entity instanceof ServerPlayer player)) return;

		TrainAssembleEvent event = NeoForge.EVENT_BUS.post(new TrainAssembleEvent(player, station.getPresentTrain(), station.getBlockEntityPos()));
		if (event.isCanceled()) ci.cancel();
	}

	@Inject(
			method = "tryDisassembleTrain",
			at = @At("HEAD"), cancellable = true
	)
	public void onDisassemble(ServerPlayer sender, CallbackInfoReturnable<Boolean> cir) {
		GlobalStation station = getStation();
		if (station == null) return;

		TrainDisassembleEvent event = NeoForge.EVENT_BUS.post(new TrainDisassembleEvent(sender, station.getPresentTrain(), station.getBlockEntityPos()));
		if (event.isCanceled()) cir.setReturnValue(false);
	}
}
