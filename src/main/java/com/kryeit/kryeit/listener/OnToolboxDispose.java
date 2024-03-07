package com.kryeit.kryeit.listener;

import com.kryeit.kryeit.event.ToolboxDisposeAllEvent;
import com.kryeit.kryeit.event.ToolboxEquipEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class OnToolboxDispose implements ToolboxDisposeAllEvent {
	@Override
	public boolean onToolboxDispose(ServerPlayer player, BlockPos toolboxPos) {
		return Utils.canBreakBlocks(player, List.of(toolboxPos));
	}
}
