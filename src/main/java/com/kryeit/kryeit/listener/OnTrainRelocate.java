package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.TrainRelocationEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class OnTrainRelocate implements TrainRelocationEvent {
	@Override
	public boolean onTrainRelocation(ServerPlayer player, Train train, BlockPos trainPos) {
		return Utils.canBreakBlocks(player, List.of(trainPos)) || (train.owner != null && train.owner.equals(player.getUUID()));
	}
}
