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
import com.kryeit.kryeit.event.TrainChangeNameEvent;
import com.kryeit.kryeit.event.TrainDisassembleEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.station.GlobalStation;
import com.simibubi.create.content.trains.station.StationBlockEntity;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

@Mixin(value = StationBlockEntity.class, remap = false)
public abstract class StationBlockEntityMixin {

	@Shadow
	@Nullable
	public abstract GlobalStation getStation();

	@Inject(method = "updateName", at = @At("RETURN"), cancellable = true)
	private void kryeit$onNameChange(String name, CallbackInfoReturnable<Boolean> cir) {
		GlobalStation station = getStation();

		if (station == null) {
			return;
		}

		ServerPlayer player = Utils.getClosestPlayer(station.blockEntityPos, station.blockEntityDimension);

		if (player == null) {
			return;
		}

		if (!TrainChangeNameEvent.EVENT.invoker().onTrainChangeName(player, station.getPresentTrain(), station.getBlockEntityPos())) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "assemble", at = @At("HEAD"), cancellable = true)
	private void kryeit$onAssemble(UUID playerUUID, CallbackInfo ci) {
		GlobalStation station = getStation();

		if (station == null) {
			return;
		}

		ResourceKey<Level> dimensionKey = station.getBlockEntityDimension();
		ServerLevel level = MinecraftServerSupplier.getServer().getLevel(dimensionKey);

		if (level == null) return;

		Entity entity = level.getEntity(playerUUID);

		if (!(entity instanceof ServerPlayer player)) return;

		if (!TrainAssembleEvent.EVENT.invoker().onTrainAssembly(player, station.getPresentTrain(), station.getBlockEntityPos())) {
			ci.cancel();
		}
	}

	@Inject(method = "tryDisassembleTrain", at = @At("HEAD"), cancellable = true)
	private void kryeit$onDisassemble(ServerPlayer sender, CallbackInfoReturnable<Boolean> cir) {
		if (sender.level().isClientSide()) return;

		GlobalStation station = getStation();

		if (station == null) {
			return;
		}

		if (!TrainDisassembleEvent.EVENT.invoker().onTrainDisassembly(sender, station.getPresentTrain(), station.getBlockEntityPos())) {
			cir.setReturnValue(false);
		}
	}
}
