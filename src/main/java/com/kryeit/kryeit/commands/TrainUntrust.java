package com.kryeit.kryeit.commands;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.commands.completion.SuggestionsProvider;
import com.kryeit.kryeit.offlines.Offlines;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class TrainUntrust {
	public static int execute(CommandContext<CommandSourceStack> context, String name) {
		CommandSourceStack source = context.getSource();
		ServerPlayer player = source.getPlayer();

		if (player == null) return 0;

		List<String> playerNames = Main.trainTrustManager.getTrustedPlayers(player.getUUID()).stream()
				.map(Offlines::getNameByUUID)
				.toList();

		if (!playerNames.contains(name)) {
			player.sendSystemMessage(Component.literal("You didn't trust " + name + " to your trains"));
			return 0;
		}

		Main.trainTrustManager.revokeTrustedPlayer(player.getUUID(), Offlines.getUUIDbyName(name));

		player.sendSystemMessage(Component.literal("You untrusted " + name + " to your trains"));
		return Command.SINGLE_SUCCESS;
	}

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("trainuntrust")
				.then(Commands.argument("player", StringArgumentType.word())
						.suggests(SuggestionsProvider.suggestTrainTrustedPlayers())
						.executes(context -> execute(context, StringArgumentType.getString(context, "player")))
				)
		);
	}
}
