package com.kryeit.kryeit.listener;


import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainStorageInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MOD_ID)
public class OnTrainStorageInteract {
	@SubscribeEvent
	public static void onTrainStorageInteract(TrainStorageInteractEvent event) {
		ServerPlayer player = event.player();
		BlockPos pos = event.pos();
		Train train = event.train();

		// Check if the player can break blocks and it's not wilderness
		if (Utils.canBreakBlocks(player, List.of(pos)) && !Utils.isWilderness(List.of(pos))) return;

		// Check if the player is the owner or a trusted player
		if (train.owner != null && (train.owner.equals(player.getUUID()) ||
				Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUUID()))) {
			return;
		}

		// Send a message if the player has no permission
		player.sendSystemMessage(Component.literal("You have no permission to access this train's storage"), true);

		// Deny the interaction
		event.setCanceled(true);
	}
}

