package com.kryeit.kryeit.commands;

import com.kryeit.kryeit.commands.completion.SuggestionsProvider;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class TrainTrust {
	public static int execute(CommandContext<CommandSourceStack> context, String name) {
		CommandSourceStack source = context.getSource();
		ServerPlayer player = source.getPlayer();

		if (player == null) return 0;

		player.sendSystemMessage(Component.literal("Hello"));
		return Command.SINGLE_SUCCESS;
	}

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("traintrust")
				.then(Commands.argument("player", StringArgumentType.word())
						.suggests(SuggestionsProvider.suggestOnlinePlayers())
						.executes(context -> execute(context, StringArgumentType.getString(context, "player")))
				)
		);
	}
}
