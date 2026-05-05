package com.kryeit.kryeit.event;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class GlueCreateEvent extends Event implements ICancellableEvent {
	private final ServerPlayer player;
	private final List<BlockPos> blocks;

	public GlueCreateEvent(ServerPlayer player, List<BlockPos> blocks) {
		this.player = player;
		this.blocks = blocks;
	}

	public List<BlockPos> blocks() {
		return blocks;
	}

	public ServerPlayer player() {
		return player;
	}
}
