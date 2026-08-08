package com.kryeit.kryeit.listener;

import com.kryeit.kryeit.event.PotatoCannonShootEvent;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class OnPotatoCannonShoot implements PotatoCannonShootEvent {

	@Override
	public boolean onCannonShoot(ServerPlayer player, ItemStack ammo) {
		return true;
	}
}
