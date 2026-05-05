package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.ControlsInteractEvent;
import com.simibubi.create.Create;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsInputPacket;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.common.NeoForge;

@Mixin(value = ControlsInputPacket.class, remap = false)
public class ControlsInputPacketMixin {

	@Final
	@Shadow
	private int contraptionEntityId;

	@Final
	@Shadow
	private BlockPos controlsPos;

	@Inject(method = "handle", remap = false, at = @At("HEAD"), cancellable = true)
	public void onHandlePlayerInteraction(ServerPlayer player, CallbackInfo ci) {
		Entity entity = player.level().getEntity(contraptionEntityId);
		if (entity == null) return;

		BlockPos pos = controlsPos;
		Train train;
		if (entity instanceof CarriageContraptionEntity carriageContraption) {
			pos = pos.offset(carriageContraption.blockPosition());
			train = Create.RAILWAYS.trains.get(carriageContraption.trainId);
		} else return;

		ControlsInteractEvent event = NeoForge.EVENT_BUS.post(new ControlsInteractEvent(player, train, pos));
		if (event.isCanceled()) {
			ci.cancel();
		}
	}
}
