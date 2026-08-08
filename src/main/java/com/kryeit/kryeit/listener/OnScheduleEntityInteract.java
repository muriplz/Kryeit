package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.ScheduleEntityInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class OnScheduleEntityInteract implements ScheduleEntityInteractEvent {
	@Override
	public boolean onScheduleEntityInteract(ServerPlayerEntity player, Train train, BlockPos seatPos) {

		// Check if the player can break blocks and it's not wilderness
		if (Utils.canBreakBlocks(player, List.of(seatPos)) && !Utils.isWilderness(player, List.of(seatPos))) {
			return true;
		}

		// Check if the player is the owner or a trusted player
		if (train.owner != null && (train.owner.equals(player.getUuid()) ||
				Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUuid()))) {
			return true;
		}

		// Send a message if the player has no permission
		player.sendMessage(Text.literal("You have no permission to interact with this train's schedule"), true);

		// Deny the interaction
		return false;
	}
}


