package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainRelocationEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class OnTrainRelocate implements TrainRelocationEvent {
	@Override
	public boolean onTrainRelocation(ServerPlayerEntity player, Train train, BlockPos from, BlockPos to) {

		// Check if the player can break blocks and neither position is in the wilderness
		if (Utils.canBreakBlocks(player, List.of(from, to))) {
			return true;
		}

		// Check if the player is the owner or a trusted player
		if (train.owner != null && (train.owner.equals(player.getUuid()) ||
				Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUuid()))) {
			return true;
		}

		// Send a message if the player has no permission
		player.sendMessage(Text.literal("You have no permission to relocate this train"), true);

		// Deny the relocation
		return false;
	}
}

