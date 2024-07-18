package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kryeit.kryeit.event.ScheduleEntityInteractEvent;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.Train;
import com.simibubi.create.content.trains.schedule.ScheduleItemEntityInteraction;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

@Mixin(ScheduleItemEntityInteraction.class)
public class ScheduleItemEntityInteractionMixin {

	@Inject(method = "interactWithConductor", at = @At("HEAD"), cancellable = true)
	private static void onHandle(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult, CallbackInfoReturnable<ActionResult> cir){
		Entity rootVehicle = entity.getRootVehicle();
		if (!(rootVehicle instanceof CarriageContraptionEntity cce))
			return;

		Train train = cce.getCarriage().train;

		Vec3d vec3d = hitResult.getPos();
		BlockPos pos = new BlockPos(new Vec3i((int) vec3d.x, (int) vec3d.y, (int) vec3d.z));

		if (!ScheduleEntityInteractEvent.EVENT.invoker().onScheduleEntityInteract((ServerPlayerEntity) player, train, pos)) {
			cir.setReturnValue(ActionResult.FAIL);
		}
	}
}
