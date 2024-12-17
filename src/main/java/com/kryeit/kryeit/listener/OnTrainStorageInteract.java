package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainStorageInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class OnTrainStorageInteract implements TrainStorageInteractEvent {
	@Override
	public boolean onTrainStorageInteract(ServerPlayerEntity player, Train train, BlockPos pos) {

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
		player.sendMessage(Text.literal("You have no permission to access this train's storage"), true);

		// Deny the interaction
		return false;
	}
}

