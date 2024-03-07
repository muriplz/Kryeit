package com.kryeit.kryeit.commands.completion;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.MinecraftServerSupplier;
import com.kryeit.kryeit.offlines.Offlines;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class SuggestionsProvider {
	public static SuggestionProvider<ServerCommandSource> suggestOnlinePlayers() {
		return (context, builder) -> suggestMatchingPlayerNames(builder, MinecraftServerSupplier.getServer().getPlayerManager().getPlayerList().stream()
				.map(ServerPlayerEntity::getGameProfile)
				.map(GameProfile::getName)
				.collect(Collectors.toList()));
	}

	public static SuggestionProvider<ServerCommandSource> suggestTrainTrustedPlayers() {
		return (context, builder) -> {
			UUID ownerUUID = context.getSource().getPlayer().getUuid();

			List<String> playerNames = Main.trainTrustManager.getTrustedPlayers(ownerUUID).stream()
					.map(Offlines::getNameByUUID)
					.toList();
			return suggestMatchingPlayerNames(builder, playerNames);
		};
	}

	private static CompletableFuture<Suggestions> suggestMatchingPlayerNames(SuggestionsBuilder builder, List<String> players) {
		String remaining = builder.getRemaining().toLowerCase();

		players.stream()
				.filter(name -> name.toLowerCase().startsWith(remaining))
				.forEach(builder::suggest);

		return builder.buildFuture();
	}
}
