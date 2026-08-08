package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.ControlsInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class OnControlsInteract implements ControlsInteractEvent {
	@Override
	public boolean onControlsInteract(ServerPlayerEntity player, Train train, BlockPos controlsPos) {

		if (Utils.canBreakBlocks(player, List.of(controlsPos)) && !Utils.isWilderness(player, List.of(controlsPos))) {
			return true;
		}

		if (train.owner != null && (train.owner.equals(player.getUuid()) || Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUuid()))) {
			return true;
		}

		player.sendMessage(Text.literal("You have no permission to use this train"), true);

		return false;
	}
}

