package com.kryeit.kryeit.listener;


import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.ScheduleEntityInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MOD_ID)
public class OnScheduleEntityInteract {
	@SubscribeEvent
	public static void onScheduleEntityInteract(ScheduleEntityInteractEvent event) {
		ServerPlayer player = event.player();
		BlockPos seatPos = event.seatPos();
		Train train = event.train();

		// Check if the player can break blocks and it's not wilderness
		if (Utils.canBreakBlocks(player, List.of(seatPos)) && !Utils.isWilderness(List.of(seatPos))) {
			return;
		}

		// Check if the player is the owner or a trusted player
		if (train.owner != null && (train.owner.equals(player.getUUID()) ||
				Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUUID()))) {
			return;
		}

		// Send a message if the player has no permission
		player.sendSystemMessage(Component.literal("You have no permission to interact with this train's schedule"), true);

		// Deny the interaction
		event.setCanceled(true);
	}
}


