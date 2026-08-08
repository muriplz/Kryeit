package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainChangeNameEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class OnTrainChangeName implements TrainChangeNameEvent {
	@Override
	public boolean onTrainChangeName(ServerPlayer player, Train train, BlockPos pos) {
		if (Utils.hasPermission(player, "group.staff")) {
			return true;
		}

		if (Utils.canBreakBlocks(player, List.of(pos)) && !Utils.isWilderness(player, List.of(pos))) {
			return true;
		}

		if (train != null && train.owner != null &&
				(train.owner.equals(player.getUUID()) ||
						Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUUID()))) {
			return true;
		}

		if (Utils.isWilderness(player, List.of(pos)) && (train == null || train.owner == null)) {
			return true;
		}

		player.displayClientMessage(Component.literal("You have no permission to change the name of this train")
				.withStyle(ChatFormatting.RED), true);
		return false;
	}
}
