package com.kryeit.kryeit.mixin.create;

import com.simibubi.create.content.contraptions.actors.contraptionControls.ContraptionControlsBlock;
import com.simibubi.create.content.contraptions.actors.contraptionControls.ContraptionControlsBlockEntity;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler;

import com.simibubi.create.content.contraptions.actors.trainControls.ControlsInputPacket;

import com.simibubi.create.foundation.networking.SimplePacketBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;

import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mixin(value = ControlsInputPacket.class, remap = false)
public class ControlsInteractionBehaviourMixin {

	@Shadow
	private int contraptionEntityId;

	@Shadow
	private BlockPos controlsPos;

	@Inject(method = "handle", remap = false, at = @At("HEAD"), cancellable = true)
	public void onHandlePlayerInteraction(SimplePacketBase.Context context, CallbackInfoReturnable<Boolean> cir) {
		Train train = Create.RAILWAYS.trains.get(UUID.fromString(contraptionEntityId+""));
		if (!ControlsInteractionEvent.EVENT.invoker().onControlsInteraction(context.sender(), train, controlsPos)) {
			cir.setReturnValue(false);
		}
	}
}
