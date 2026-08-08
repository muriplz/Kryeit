package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.ClipboardEditEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class OnClipboardEdit implements ClipboardEditEvent {

	@Override
	public boolean onClipboardEdit(ServerPlayer player, ClipboardBlockEntity clipboard, BlockPos pos) {
		return Utils.canBreakBlocks(player, List.of(pos));
	}
}
