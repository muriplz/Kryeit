package com.kryeit.kryeit.listener;

import com.kryeit.kryeit.event.ClipboardEditEvent;
import com.kryeit.kryeit.event.FilterInteractEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class OnFilterInteract implements FilterInteractEvent {

	@Override
	public boolean onFilterInteract(ServerPlayerEntity player, BlockPos pos) {
		return Utils.canBreakBlocks(player, List.of(pos));
	}
}
