package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.ControlsInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MOD_ID)
public class OnControlsInteract {
	@SubscribeEvent
	public static void onControlsInteract(ControlsInteractEvent event) {
		ServerPlayer player = event.player();
		BlockPos controlsPos = event.controlsPos();
		Train train = event.train();

		if (Utils.canBreakBlocks(player, List.of(controlsPos)) && !Utils.isWilderness(List.of(controlsPos))) {
			return;
		}

		if (train.owner != null && (train.owner.equals(player.getUUID()) || Main.trainTrustManager.getTrustedPlayers(train.owner).contains(player.getUUID()))) {
			return;
		}

		player.sendSystemMessage(Component.literal("You have no permission to use this train"), true);
		event.setCanceled(true);
	}
}

