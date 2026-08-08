package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.TrainAssembleEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class OnTrainAssemble implements TrainAssembleEvent {
	@Override
	public boolean onTrainAssembly(ServerPlayer player, Train train, BlockPos pos) {
		if (Utils.hasPermission(player, "group.staff")) {
			return true;
		}

		if (Utils.canBreakBlocks(player, List.of(pos))) {
			return true;
		}

		player.displayClientMessage(Component.literal("You have no permission to assemble this train")
				.withStyle(ChatFormatting.RED), true);

		return false;
	}
}
