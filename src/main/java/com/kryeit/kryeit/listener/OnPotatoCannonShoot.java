package com.kryeit.kryeit.listener;

import java.util.Arrays;
import java.util.List;

import com.kryeit.kryeit.event.PotatoCannonShootEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class OnPotatoCannonShoot implements PotatoCannonShootEvent {

	@Override
	public boolean onCannonShoot(ServerPlayerEntity player, ItemStack ammo) {

		List<String> bannedAmmo = Arrays.asList(
				"item.create.blaze_cake",
				"item.minecraft.baked_potato"
		);

		String key = ammo.getTranslationKey();
        return !bannedAmmo.contains(key);
    }
}
