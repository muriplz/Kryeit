package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.ControlsInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class OnControlsInteract implements ControlsInteractEvent {
	@Override
	public boolean onControlsInteract(ServerPlayer player, Train train, BlockPos controlsPos) {

		if (Utils.canBreakBlocks(player, List.of(controlsPos)) && !Utils.isWilderness(player, List.of(controlsPos))) {
			return true;
		}

		if (train.owner != null && (train.owner.equals(player.getUUID()) || Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUUID()))) {
			return true;
		}

		player.displayClientMessage(Component.literal("You have no permission to use this train"), true);

		return false;
	}
}

