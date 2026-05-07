package com.kryeit.kryeit.listener;


import java.util.List;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.FilterInteractEvent;
import com.kryeit.kryeit.utils.Utils;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MOD_ID)
public class OnFilterInteract {

	@SubscribeEvent
	public static void onFilterInteract(FilterInteractEvent event) {
		event.setCanceled(!Utils.canBreakBlocks(event.player(), List.of(event.pos())));
	}
}
