package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.ScheduleEntityInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class OnScheduleEntityInteract implements ScheduleEntityInteractEvent {
	@Override
	public boolean onScheduleEntityInteract(ServerPlayer player, Train train, BlockPos seatPos) {

		// Check if the player can break blocks and it's not wilderness
		if (Utils.canBreakBlocks(player, List.of(seatPos)) && !Utils.isWilderness(player, List.of(seatPos))) {
			return true;
		}

		// Check if the player is the owner or a trusted player
		if (train.owner != null && (train.owner.equals(player.getUUID()) ||
				Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUUID()))) {
			return true;
		}

		// Send a message if the player has no permission
		player.displayClientMessage(Component.literal("You have no permission to interact with this train's schedule"), true);

		// Deny the interaction
		return false;
	}
}
