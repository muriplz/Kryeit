package com.kryeit.kryeit.event;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class ToolboxEquipEvent extends Event implements ICancellableEvent {
	private final ServerPlayer player;
	private final BlockPos toolboxPos;

	public ToolboxEquipEvent(ServerPlayer player, BlockPos toolboxPos) {
		this.player = player;
		this.toolboxPos = toolboxPos;
	}

	public ServerPlayer player() {
		return player;
	}

	public BlockPos toolboxPos() {
		return toolboxPos;
	}
}
