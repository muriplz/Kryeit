package com.kryeit.kryeit.commands;

import java.util.UUID;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.commands.completion.SuggestionsProvider;
import com.kryeit.kryeit.offlines.Offlines;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;


public class TrainTrust {
	public static int execute(CommandContext<ServerCommandSource> context, String name) {
		ServerCommandSource source = context.getSource();
		ServerPlayerEntity player = source.getPlayer();

		if (player == null) return 0;

		UUID trustedUUID = Offlines.getUUIDbyName(name);
		if (trustedUUID == null) {
			player.sendMessage(Text.literal("Player " + name + " was not found"));
			return 0;
		}

		if (Main.trainTrustManager.getTrustedPlayers(player.getUuid()).contains(trustedUUID)) {
			player.sendMessage(Text.literal("You already trusted " + name + " to your trains"));
			return 0;
		}

		Main.trainTrustManager.addTrustedPlayer(player.getUuid(), trustedUUID);
		player.sendMessage(Text.literal("You trusted " + name + " to your trains"));
		return Command.SINGLE_SUCCESS;
	}

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(CommandManager.literal("traintrust")
				.then(CommandManager.argument("player", StringArgumentType.word())
						.suggests(SuggestionsProvider.suggestOfflinePlayers())
						.executes(context -> execute(context, StringArgumentType.getString(context, "player")))
				)
		);
	}
}
