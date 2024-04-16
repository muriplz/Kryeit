package com.kryeit.kryeit.utils;

import java.util.List;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.TrustTypes;

import com.kryeit.kryeit.MinecraftServerSupplier;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class Utils {
	public static boolean canBreakBlocks(ServerPlayerEntity player, List<BlockPos> blocks) {
		Claim claim;

		for (BlockPos block : blocks) {
			claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(player.getWorld()), block.getX(), block.getY(), block.getZ());
			if (claim == null || claim.isWilderness()) continue;

			if (!claim.isUserTrusted(player.getUuid(), TrustTypes.BUILDER)){
				return false;
			}
		}
		return true;
	}

	public static void broadcast(String s) {
		MinecraftServerSupplier.getServer().getPlayerManager().broadcast(
				Text.literal(s),
				false
		);
	}
}
