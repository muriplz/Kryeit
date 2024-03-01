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

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;

@Mixin(ControlsInteractionBehaviour.class)
public class ControlsInteractionBehaviourMixin {

	@Inject(method = "handlePlayerInteraction", remap = false, at = @At("HEAD"), cancellable = true)
	public void onHandlePlayerInteraction(Player player, InteractionHand activeHand, BlockPos localPos, AbstractContraptionEntity contraptionEntity, CallbackInfoReturnable<Boolean> cir) {

		Train train = Create.RAILWAYS.trains.get(contraptionEntity.getContraption().entity.getUUID());
		if (!ControlsInteractionEvent.EVENT.invoker().onControlsInteraction((ServerPlayer) player, train, BlockPos.containing(player.position().add(localPos.getCenter())))) {
			cir.setReturnValue(false);
		}
	}
}
