package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;


public class ScheduleEntityInteractEvent extends Event implements ICancellableEvent {
	private final ServerPlayer player;
	private final Train train;
	private final BlockPos seatPos;

	public ScheduleEntityInteractEvent(ServerPlayer player, Train train, BlockPos seatPos) {
		this.player = player;
		this.train = train;
		this.seatPos = seatPos;
	}

	public ServerPlayer player() {
		return player;
	}

	public Train train() {
		return train;
	}

	public BlockPos seatPos() {
		return seatPos;
	}
}
