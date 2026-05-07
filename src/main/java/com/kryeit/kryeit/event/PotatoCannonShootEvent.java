package com.kryeit.kryeit.event;


import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class PotatoCannonShootEvent extends Event implements ICancellableEvent {
	private final ServerPlayer player;
	private final ItemStack ammo;

	public PotatoCannonShootEvent(ServerPlayer player, ItemStack ammo) {
		this.player = player;
		this.ammo = ammo;
	}

	public ServerPlayer player() {
		return player;
	}

	public ItemStack ammo() {
		return ammo;
	}
}
