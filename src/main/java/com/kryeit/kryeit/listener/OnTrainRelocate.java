package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainRelocationEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class OnTrainRelocate implements TrainRelocationEvent {
	@Override
	public boolean onTrainRelocation(ServerPlayerEntity player, Train train, BlockPos from, BlockPos to) {
		if (Permissions.check(player, "group.staff")) {
			return true;
		}

		if (Utils.canBreakBlocks(player, List.of(from, to)) && !Utils.isWilderness(player, List.of(from, to))) {
			return true;
		}

		if (train != null && train.owner != null &&
				(train.owner.equals(player.getUuid()) ||
						Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUuid()))) {
			return true;
		}

		if (Utils.isWilderness(player, List.of(from, to)) && (train == null || train.owner == null)) {
			return true;
		}

		player.sendMessage(Text.literal("You have no permission to relocate this train"), true);

		return false;
	}
}

