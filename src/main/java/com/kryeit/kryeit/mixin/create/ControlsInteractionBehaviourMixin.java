package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kryeit.kryeit.event.ControlsInteractionEvent;
import com.simibubi.create.Create;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsInteractionBehaviour;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

@Mixin(ControlsInteractionBehaviour.class)
public class ControlsInteractionBehaviourMixin {

	@Inject(method = "handlePlayerInteraction", remap = false, at = @At("HEAD"), cancellable = true)
	public void onHandlePlayerInteraction(PlayerEntity player, Hand activeHand, BlockPos localPos, AbstractContraptionEntity contraptionEntity, CallbackInfoReturnable<Boolean> cir) {

		Train train = Create.RAILWAYS.trains.get(contraptionEntity.getContraption().entity.getUuid());
		Vec3d position = player.getPos().add(localPos.toCenterPos());
		if (!ControlsInteractionEvent.EVENT.invoker().onControlsInteraction((ServerPlayerEntity) player, train, new BlockPos((int) position.x, (int) position.y, (int) position.z))) {
			cir.setReturnValue(false);
		}
	}
}
