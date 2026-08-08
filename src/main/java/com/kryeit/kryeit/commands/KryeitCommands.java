package com.kryeit.kryeit.commands;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/**
 * Registers the /traintrust and /trainuntrust commands on the NeoForge game bus,
 * replacing Fabric's CommandRegistrationCallback.
 */
public class KryeitCommands {

	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		TrainTrust.register(event.getDispatcher());
		TrainUntrust.register(event.getDispatcher());
	}
}
