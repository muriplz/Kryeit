package com.kryeit.kryeit.listener;


import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.GlueKillEvent;
import com.kryeit.kryeit.utils.Utils;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MOD_ID)
public class OnKillGlue {

	@SubscribeEvent
	public static void onKillGlue(GlueKillEvent event) {
		event.setCanceled(!Utils.canBreakBlocks(event.player(), event.blocks()));
	}
}
