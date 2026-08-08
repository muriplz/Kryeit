package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.ScheduleEntityInteractEvent;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.Train;
import com.simibubi.create.content.trains.schedule.ScheduleItemEntityInteraction;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;

// Create 6 turned this into a NeoForge @SubscribeEvent handler:
// interactWithConductor(PlayerInteractEvent.EntityInteractSpecific) -> void.
@Mixin(value = ScheduleItemEntityInteraction.class, remap = false)
public class ScheduleItemEntityInteractionMixin {

	@Inject(method = "interactWithConductor", at = @At("HEAD"), cancellable = true)
	private static void kryeit$onHandle(EntityInteractSpecific event, CallbackInfo ci) {
		Entity entity = event.getTarget();
		if (entity == null)
			return;

		if (!(event.getEntity() instanceof ServerPlayer serverPlayer))
			return;

		if (!(entity.getRootVehicle() instanceof CarriageContraptionEntity cce))
			return;

		Train train = cce.getCarriage().train;
		BlockPos pos = entity.blockPosition();

		if (!ScheduleEntityInteractEvent.EVENT.invoker().onScheduleEntityInteract(serverPlayer, train, pos)) {
			event.setCancellationResult(InteractionResult.FAIL);
			event.setCanceled(true);
			ci.cancel();
		}
	}
}
