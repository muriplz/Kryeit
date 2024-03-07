package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ToolboxDisposeAllEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class OnToolboxDispose implements ToolboxDisposeAllEvent {
	@Override
	public boolean onToolboxDispose(ServerPlayerEntity player, BlockPos toolboxPos) {
		return Utils.canBreakBlocks(player, List.of(toolboxPos));
	}
}
