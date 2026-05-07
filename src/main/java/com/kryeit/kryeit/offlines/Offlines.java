package com.kryeit.kryeit.offlines;

import java.util.UUID;

import com.kryeit.kryeit.MinecraftServerSupplier;
import com.mojang.authlib.GameProfile;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.GameProfileCache;


public class Offlines {

	public static UUID getUUIDbyName(String name) {
		ServerPlayer player = MinecraftServerSupplier.getServer().getPlayerList().getPlayerByName(name);
		if (player != null) return player.getUUID();

		GameProfileCache userCache = MinecraftServerSupplier.getServer().getProfileCache();
		if (userCache == null) return null;

		return userCache.get(name)
				.map(GameProfile::getId)
				.orElse(null);
	}

	public static String getNameByUUID(UUID id) {
		ServerPlayer player = MinecraftServerSupplier.getServer().getPlayerList().getPlayer(id);
		if (player != null) return player.getName().getString();

		GameProfileCache userCache = MinecraftServerSupplier.getServer().getProfileCache();
		if (userCache == null) return "";

		return userCache.get(id)
				.map(GameProfile::getName)
				.orElse("");
	}
}
