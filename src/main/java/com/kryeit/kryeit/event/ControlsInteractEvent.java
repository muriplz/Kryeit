package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;


public class ControlsInteractEvent extends Event implements ICancellableEvent {
	private final ServerPlayer player;
	private final Train train;
	private final BlockPos controlsPos;

	public ControlsInteractEvent(ServerPlayer player, Train train, BlockPos controlsPos) {
		this.player = player;
		this.train = train;
		this.controlsPos = controlsPos;
	}

	public BlockPos controlsPos() {
		return controlsPos;
	}

	public Train train() {
		return train;
	}

	public ServerPlayer player() {
		return player;
	}
}
