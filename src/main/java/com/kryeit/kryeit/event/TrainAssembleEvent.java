package com.kryeit.kryeit.event;


import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class TrainAssembleEvent extends Event implements ICancellableEvent {
	private final ServerPlayer player;
	private final Train train;
	private final BlockPos pos;

	public TrainAssembleEvent(ServerPlayer player, Train train, BlockPos pos) {
		this.player = player;
		this.train = train;
		this.pos = pos;
	}

	public ServerPlayer player() {
		return player;
	}

	public Train train() {
		return train;
	}

	public BlockPos pos() {
		return pos;
	}
}
