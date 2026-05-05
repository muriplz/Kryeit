package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.ToolboxPickupEvent;
import com.kryeit.kryeit.utils.Utils;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MOD_ID)
public class OnToolboxPickup {
	@SubscribeEvent
	public static void onToolboxPickup(ToolboxPickupEvent event) {
		return event.setCanceled(!Utils.canBreakBlocks(event.player(), List.of(event.toolboxPos())));
	}
}
