package com.kryeit.kryeit.listener;

import java.util.List;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.User;
import com.griefdefender.api.claim.Claim;
import com.kryeit.kryeit.event.GlueCreateEvent;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class OnCreateGlue implements GlueCreateEvent {

	@Override
	public boolean onCreateGlue(ServerPlayer player, List<BlockPos> blocks) {

		final User user = GriefDefender.getCore().getUser(player.getUUID());
		Claim claim;

		for (BlockPos block : blocks) {
			claim = GriefDefender.getCore().getClaimAt(block);
			if (claim != null) {
				if (!claim.canBreak(player, block, user)) {
					return false;
				}
			}
		}

		return false;
	}
}
