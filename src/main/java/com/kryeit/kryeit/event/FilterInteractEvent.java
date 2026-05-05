package com.kryeit.kryeit.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class FilterInteractEvent extends Event implements ICancellableEvent {
	private final ServerPlayer player;
	private final BlockPos pos;
	private final BlockState state;

	public FilterInteractEvent(ServerPlayer player, BlockPos pos, BlockState state) {
		this.player = player;
		this.pos = pos;
		this.state = state;
	}

	public BlockState state() {
		return state;
	}

	public BlockPos pos() {
		return pos;
	}

	public ServerPlayer player() {
		return player;
	}
}
