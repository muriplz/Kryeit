package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ToolboxPickupEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class OnToolboxPickup implements ToolboxPickupEvent {
	@Override
	public boolean onToolboxPickup(ServerPlayerEntity player, BlockPos toolboxPos) {
		return Utils.canBreakBlocks(player, List.of(toolboxPos));
	}
}
