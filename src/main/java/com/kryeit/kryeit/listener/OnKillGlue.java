package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.GlueKillEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class OnKillGlue implements GlueKillEvent {

	@Override
	public boolean onKillGlue(ServerPlayer player, List<BlockPos> blocks) {
		return Utils.canBreakBlocks(player, blocks);
	}
}
