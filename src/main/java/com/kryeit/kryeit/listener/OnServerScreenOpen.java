package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ServersideModdedScreenOpenEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class OnServerScreenOpen implements ServersideModdedScreenOpenEvent {
	@Override
	public boolean onGUIOpen(ServerPlayerEntity player, BlockEntity blockEntity) {
		return Utils.canBreakBlocks(player, List.of(blockEntity.getPos()));
	}
}
