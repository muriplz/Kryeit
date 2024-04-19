package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ClipboardEditEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class OnClipboardEdit implements ClipboardEditEvent {

	@Override
	public boolean onClipboardEdit(ServerPlayerEntity player, ClipboardBlockEntity clipboard, BlockPos pos) {
		return Utils.canBreakBlocks(player, List.of(pos));
	}
}
