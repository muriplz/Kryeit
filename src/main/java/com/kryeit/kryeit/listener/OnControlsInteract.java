package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ControlsInteractionEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class OnControlsInteract implements ControlsInteractionEvent {
	@Override
	public boolean onControlsInteraction(ServerPlayerEntity player, Train train, BlockPos controlsPos) {
		Utils.broadcast("Train controls interacted with at " + controlsPos + "\nTrain owner: " + train.owner + "\nPlayer: " + player.getUuid());
		if (train.owner == null) return true;
		return Utils.canBreakBlocks(player, List.of(controlsPos)) || train.owner.equals(player.getUuid());
	}
}
