package com.kryeit.kryeit.listener;


import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.GlueCreateEvent;
import com.kryeit.kryeit.utils.Utils;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MOD_ID)
public class OnCreateGlue {

	@SubscribeEvent
	public static void onCreateGlue(GlueCreateEvent event) {
		event.setCanceled(!Utils.canBreakBlocks(event.player(), event.blocks()));
	}
}
