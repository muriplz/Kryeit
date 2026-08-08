package com.kryeit.kryeit.offlines;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.kryeit.kryeit.MinecraftServerSupplier;
import com.mojang.authlib.GameProfile;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.GameProfileCache;

public class Offlines {

	public static UUID getUUIDbyName(String name) {
		ServerPlayer player = MinecraftServerSupplier.getServer().getPlayerList().getPlayerByName(name);
		if (player != null) return player.getUUID();
		GameProfileCache cache = MinecraftServerSupplier.getServer().getProfileCache();
		if (cache == null) return null;
		Optional<GameProfile> gameProfile = cache.get(name);
		return gameProfile.map(GameProfile::getId).orElse(null);
	}

	public static String getNameByUUID(UUID id) {
		ServerPlayer player = MinecraftServerSupplier.getServer().getPlayerList().getPlayer(id);
		if (player != null) return player.getName().getString();
		GameProfileCache cache = MinecraftServerSupplier.getServer().getProfileCache();
		if (cache == null) return "";
		Optional<GameProfile> gameProfile = cache.get(id);
		return gameProfile.map(GameProfile::getName).orElse("");
	}

	public static List<String> getPlayerNames() {
		List<String> players = new ArrayList<>();
		File playerDataDirectory = new File("world/playerdata/");

		File[] playerDataFiles = playerDataDirectory.listFiles();

		if (playerDataFiles == null) return List.of();

		for (File playerDataFile : playerDataFiles) {
			String fileName = playerDataFile.getName();
			if (!fileName.endsWith(".dat")) continue;
			UUID id = UUID.fromString(fileName.substring(0, fileName.length() - 4));
			players.add(getNameByUUID(id));
		}
		return players;
	}
}
