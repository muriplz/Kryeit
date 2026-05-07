package com.kryeit.kryeit.listener;


import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainChangeNameEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import com.kryeit.kryeit.compat.Permissions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MOD_ID)
public class OnTrainChangeName {
	@SubscribeEvent
	public static void onTrainChangeName(TrainChangeNameEvent event) {
		ServerPlayer player = event.player();
		BlockPos pos = event.pos();
		Train train = event.train();

		if (Permissions.check(player, "group.staff")) return;

		// Check if the player can break blocks and it's not wilderness
		if (Utils.canBreakBlocks(player, List.of(pos)) && !Utils.isWilderness(List.of(pos))) return;

		if (Utils.isWilderness(List.of(pos))) {
			if (train == null) {
				return;
			} else {
				if (train.owner == null) return;

				// Check if the player is the owner or a trusted player
				if ((train.owner.equals(player.getUUID()) ||
						Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUUID()))) {
					return;
				}
			}
		}

		// Send a message if the player has no permission
		player.sendSystemMessage(Component.literal("You have no permission to change the name of this train")
				.setStyle(Style.EMPTY.withColor(ChatFormatting.RED)), true);
		// Deny the action
		event.setCanceled(true);
	}
}

