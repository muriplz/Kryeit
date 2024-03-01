package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ControlsInteractionEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class OnControlsInteract implements ControlsInteractionEvent {
	@Override
	public boolean onControlsInteraction(ServerPlayer player, Train train, BlockPos trainPos) {
		return Utils.canBreakBlocks(player, List.of(trainPos)) || (train.owner != null && train.owner.equals(player.getUUID()));
	}
}
