package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.GlueKillEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class OnKillGlue implements GlueKillEvent {

	@Override
	public boolean onKillGlue(ServerPlayerEntity player, List<BlockPos> blocks) {
		return Utils.canBreakBlocks(player, blocks);
	}
}
