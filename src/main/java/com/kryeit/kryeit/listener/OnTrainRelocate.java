package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainRelocationEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class OnTrainRelocate implements TrainRelocationEvent {
	@Override
	public boolean onTrainRelocation(ServerPlayer player, Train train, BlockPos from, BlockPos to) {
		if (Utils.hasPermission(player, "group.staff")) {
			return true;
		}

		if (Utils.canBreakBlocks(player, List.of(from, to)) && !Utils.isWilderness(player, List.of(from, to))) {
			return true;
		}

		if (train != null && train.owner != null &&
				(train.owner.equals(player.getUUID()) ||
						Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUUID()))) {
			return true;
		}

		if (Utils.isWilderness(player, List.of(from, to)) && (train == null || train.owner == null)) {
			return true;
		}

		player.displayClientMessage(Component.literal("You have no permission to relocate this train"), true);

		return false;
	}
}

