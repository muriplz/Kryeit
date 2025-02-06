package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainAssembleEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

public class OnTrainAssemble implements TrainAssembleEvent {
	@Override
	public boolean onTrainAssembly(ServerPlayerEntity player, Train train, BlockPos pos) {
		if (Permissions.check(player, "group.staff")) {
			return true;
		}

		// Check if the player can break blocks and it's not wilderness
		if (Utils.canBreakBlocks(player, List.of(pos)) && !Utils.isWilderness(List.of(pos))) {
			return true;
		}

		if (Utils.isWilderness(List.of(pos))) {

			if (train == null) {
				return true;
			} else {
				if (train.owner == null) {
					return true;
				}

				// Check if the player is the owner or a trusted player
				if ((train.owner.equals(player.getUuid()) ||
						Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUuid()))) {
					return true;
				}
			}
		}

		// Send a message if the player has no permission
		player.sendMessage(Text.literal("You have no permission to assemble this train")
				.setStyle(Style.EMPTY.withColor(Formatting.RED)), true);

		// Deny the action
		return false;
	}
}

