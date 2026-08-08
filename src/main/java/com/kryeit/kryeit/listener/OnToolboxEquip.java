package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ToolboxEquipEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class OnToolboxEquip implements ToolboxEquipEvent {
	@Override
	public boolean onToolboxEquip(ServerPlayer player, BlockPos toolboxPos) {
		return Utils.canBreakBlocks(player, List.of(toolboxPos));
	}
}
