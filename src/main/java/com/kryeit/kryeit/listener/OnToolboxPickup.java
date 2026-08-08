package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ToolboxPickupEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class OnToolboxPickup implements ToolboxPickupEvent {
	@Override
	public boolean onToolboxPickup(ServerPlayer player, BlockPos toolboxPos) {
		return Utils.canBreakBlocks(player, List.of(toolboxPos));
	}
}
