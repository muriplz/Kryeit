package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.GlueCreateEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class OnCreateGlue implements GlueCreateEvent {

	@Override
	public boolean onCreateGlue(ServerPlayer player, List<BlockPos> blocks) {
		return Utils.canBreakBlocks(player, blocks);
	}
}
