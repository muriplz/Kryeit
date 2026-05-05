package com.kryeit.kryeit.event;

import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class ClipboardEditEvent extends BlockEvent implements ICancellableEvent {
	private final ServerPlayer player;
	private final ClipboardBlockEntity clipboard;

	public ClipboardEditEvent(ServerLevel level, BlockPos pos, BlockState state, ServerPlayer player, ClipboardBlockEntity clipboard) {
		super(level, pos, state);

		this.player = player;
		this.clipboard = clipboard;
	}

	public ServerPlayer player() {
		return player;
	}

	public ClipboardBlockEntity clipboard() {
		return clipboard;
	}
}
