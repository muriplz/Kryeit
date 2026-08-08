package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainDisassembleEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

public class OnTrainDisassemble implements TrainDisassembleEvent {
	@Override
	public boolean onTrainDisassembly(ServerPlayerEntity player, Train train, BlockPos pos) {
		if (Permissions.check(player, "group.staff")) {
			return true;
		}

		if (Utils.canBreakBlocks(player, List.of(pos)) && !Utils.isWilderness(player, List.of(pos))) {
			return true;
		}

		if (train != null && train.owner != null &&
				(train.owner.equals(player.getUuid()) ||
						Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUuid()))) {
			return true;
		}

		if (Utils.isWilderness(player, List.of(pos)) && (train == null || train.owner == null)) {
			return true;
		}

		player.sendMessage(Text.literal("You have no permission to disassemble this train")
				.setStyle(Style.EMPTY.withColor(Formatting.RED)), true);

		return false;
	}
}

