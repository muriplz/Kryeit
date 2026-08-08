package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.FilterInteractEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class OnFilterInteract implements FilterInteractEvent {

	@Override
	public boolean onFilterInteract(ServerPlayer player, BlockPos pos) {
		return Utils.canBreakBlocks(player, List.of(pos));
	}
}
