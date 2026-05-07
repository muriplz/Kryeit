package com.kryeit.kryeit.listener;


import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.TrainRelocationEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MOD_ID)
public class OnTrainRelocate {
	@SubscribeEvent
	public static void onTrainRelocation(TrainRelocationEvent event) {
		ServerPlayer player = event.player();
		Train train = event.train();

		// Check if the player can break blocks and neither position is in the wilderness
		if (Utils.canBreakBlocks(player, List.of())) return;

		// Check if the player is the owner or a trusted player
		if (train.owner != null && (train.owner.equals(player.getUUID()) ||
				Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUUID()))) {
			return;
		}

		// Send a message if the player has no permission
		player.sendSystemMessage(Component.literal("You have no permission to relocate this train"), true);

		// Deny the relocation
		event.setCanceled(true);
	}
}

