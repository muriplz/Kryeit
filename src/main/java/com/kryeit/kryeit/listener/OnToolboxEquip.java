package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ToolboxEquipEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class OnToolboxEquip implements ToolboxEquipEvent {
	@Override
	public boolean onToolboxEquip(ServerPlayerEntity player, BlockPos toolboxPos) {
		return Utils.canBreakBlocks(player, List.of(toolboxPos));
	}
}
