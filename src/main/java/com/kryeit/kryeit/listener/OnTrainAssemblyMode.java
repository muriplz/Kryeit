package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainAssemblyModeEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class OnTrainAssemblyMode implements TrainAssemblyModeEvent {
	@Override
	public boolean onTrainAssembly(ServerPlayerEntity player, Train train, BlockPos pos) {

		// Check if the player can break blocks and it's not wilderness
		if (Utils.canBreakBlocks(player, List.of(pos)) && !Utils.isWilderness(List.of(pos))) {
			return true;
		}

		// Check if the player is the owner or a trusted player
		if (train.owner != null && (train.owner.equals(player.getUuid()) ||
				Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUuid()))) {
			return true;
		}

		// Send a message if the player has no permission
		player.sendMessage(Text.literal("You have no permission to assemble this train"), true);

		// Deny the action
		return false;
	}
}

