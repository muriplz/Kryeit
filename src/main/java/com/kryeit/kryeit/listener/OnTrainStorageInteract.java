package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.TrainStorageInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class OnTrainStorageInteract implements TrainStorageInteractEvent {
	@Override
	public boolean onTrainStorageInteract(ServerPlayerEntity player, Train train, BlockPos pos) {
		return Utils.canBreakBlocks(player, List.of(pos)) || (train.owner != null && train.owner.equals(player.getUuid()));
	}
}
