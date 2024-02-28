package com.kryeit.kryeit.listener;

import java.util.List;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.User;
import com.griefdefender.api.claim.Claim;
import com.kryeit.kryeit.event.GlueKillEvent;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class OnKillGlue implements GlueKillEvent {

	@Override
	public boolean onKillGlue(ServerPlayer player, List<BlockPos> blocks) {
		final User user = GriefDefender.getCore().getUser(player.getUUID());

		for (BlockPos block : blocks) {
			Claim claim = GriefDefender.getCore().getClaimAt(block);
			if (claim != null) {
				if (!claim.canBreak(player, block, user)) {
					return false;
				}
			}
		}
		return true;
	}
}
