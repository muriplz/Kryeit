package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.ScheduleEntityInteractEvent;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.Train;
import com.simibubi.create.content.trains.schedule.ScheduleItemEntityInteraction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;


@Mixin(ScheduleItemEntityInteraction.class)
public class ScheduleItemEntityInteractionMixin {

	@Inject(method = "interactWithConductor", at = @At("HEAD"), cancellable = true)
	private static void onHandle(PlayerInteractEvent.EntityInteractSpecific event, CallbackInfo ci) {
		Entity rootVehicle = event.getEntity().getRootVehicle();
		if (!(rootVehicle instanceof CarriageContraptionEntity cce))
			return;

		Train train = cce.getCarriage().train;

		Vec3 vec3d = event.getLocalPos();
		BlockPos pos = new BlockPos(new Vec3i((int) vec3d.x, (int) vec3d.y, (int) vec3d.z));

		ScheduleEntityInteractEvent postedEvent = Main.MOD_BUS.post(new ScheduleEntityInteractEvent((ServerPlayer) event.getEntity(), train, pos));
		if (event.isCanceled()) ci.cancel();
	}
}
