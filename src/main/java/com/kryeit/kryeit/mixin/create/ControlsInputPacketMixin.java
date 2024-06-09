package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kryeit.kryeit.event.ControlsInteractEvent;
import com.simibubi.create.Create;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsInputPacket;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.Train;
import com.simibubi.create.foundation.networking.SimplePacketBase;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

@Mixin(value = ControlsInputPacket.class, remap = false)
public class ControlsInputPacketMixin {

	@Shadow
	private int contraptionEntityId;

	@Shadow
	private BlockPos controlsPos;

	@Inject(method = "handle", remap = false, at = @At("HEAD"), cancellable = true)
	public void onHandlePlayerInteraction(SimplePacketBase.Context context, CallbackInfoReturnable<Boolean> cir) {
		Entity entity = context.sender().getWorld().getEntityById(contraptionEntityId);

		if (entity == null) return;

		BlockPos pos = controlsPos;
		Train train;
		if (entity instanceof CarriageContraptionEntity carriageContraption) {
			pos = pos.add(carriageContraption.getBlockPos());
			train = Create.RAILWAYS.trains.get(carriageContraption.trainId);
		} else return;

		if (!ControlsInteractEvent.EVENT.invoker().onControlsInteract(context.sender(), train, pos)) {
			cir.setReturnValue(false);
		}
	}
}
