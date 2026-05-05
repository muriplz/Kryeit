package com.kryeit.kryeit.listener;


import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.PotatoCannonShootEvent;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MOD_ID)
public class OnPotatoCannonShoot {

	@SubscribeEvent
	public static void onCannonShoot(PotatoCannonShootEvent event) {

//		List<String> bannedAmmo = Arrays.asList(
//				"item.create.blaze_cake",
//				"item.minecraft.baked_potato"
//		);
//
//		String key = ammo.getTranslationKey();
//        return !bannedAmmo.contains(key);
	}
}
