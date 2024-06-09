package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ScheduleEntityInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class OnScheduleEntityInteract implements ScheduleEntityInteractEvent {
	@Override
	public boolean onScheduleEntityInteract(ServerPlayerEntity player, Train train, BlockPos seatPos) {
		return Utils.canBreakBlocks(player, List.of(seatPos)) || (train.owner != null && train.owner.equals(player.getUuid()));
	}
}

