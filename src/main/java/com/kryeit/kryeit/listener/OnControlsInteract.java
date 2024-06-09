package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ControlsInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class OnControlsInteract implements ControlsInteractEvent {
	@Override
	public boolean onControlsInteract(ServerPlayerEntity player, Train train, BlockPos controlsPos) {
		return Utils.canBreakBlocks(player, List.of(controlsPos)) || (train.owner != null && train.owner.equals(player.getUuid()));
	}
}

