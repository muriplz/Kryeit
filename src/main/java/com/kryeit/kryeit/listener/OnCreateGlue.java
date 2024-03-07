package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.GlueCreateEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class OnCreateGlue implements GlueCreateEvent {

	@Override
	public boolean onCreateGlue(ServerPlayerEntity player, List<BlockPos> blocks) {
		return Utils.canBreakBlocks(player, blocks);
	}
}
