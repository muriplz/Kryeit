package com.kryeit.kryeit.utils;

import java.util.List;
import java.util.UUID;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.TrustTypes;

import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class Utils {
	public static boolean canBreakBlocks(ServerPlayer player, List<BlockPos> blocks) {
		Claim claim;

		for (BlockPos block : blocks) {
			claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(player.level()), block.getX(), block.getY(), block.getZ());
			if (claim == null || claim.isWilderness()) continue;

			if (!claim.isUserTrusted(player.getUUID(), TrustTypes.BUILDER)){
				if (!claim.canBreak(player, block, null)) {
					return false;
				}
			}
		}
		return true;
	}

}
