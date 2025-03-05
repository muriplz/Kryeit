package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.FilterInteractEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class OnFilterInteract implements FilterInteractEvent {

	@Override
	public boolean onFilterInteract(ServerPlayerEntity player, BlockPos pos) {
		return Utils.canBreakBlocks(player, List.of(pos));
	}
}
