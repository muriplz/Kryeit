package com.kryeit.kryeit.utils;

import java.util.List;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.TrustTypes;
import com.kryeit.kryeit.MinecraftServerSupplier;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;


public class Utils {
	public static boolean isWilderness(List<BlockPos> blocks) {
		Claim claim;

		for (BlockPos block : blocks) {
			claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(MinecraftServerSupplier.getServer().overworld()), block.getX(), block.getY(), block.getZ());
			if (claim == null || claim.isWilderness()) continue;

			return false;
		}
		return true;
	}

	public static boolean canBreakBlocks(ServerPlayer player, List<BlockPos> blocks) {
		Claim claim;

		for (BlockPos block : blocks) {
			claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(player.level()), block.getX(), block.getY(), block.getZ());
			if (claim == null || claim.isWilderness()) continue;

			if (!hasTrustInBlock(player, block)) {
				return false;
			}
		}
		return true;
	}

	public static boolean hasTrustInBlock(ServerPlayer player, BlockPos block) {
		Claim claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(player.level()), block.getX(), block.getY(), block.getZ());
		if (claim == null || claim.isWilderness()) return true;

		return claim.isUserTrusted(player.getUUID(), TrustTypes.BUILDER);
	}

	public static ServerPlayer getClosestPlayer(BlockPos blockEntityPos, ResourceKey<Level> blockEntityDimension) {
		return (ServerPlayer) MinecraftServerSupplier.getServer()
				.getLevel(blockEntityDimension)
				.getNearestPlayer(blockEntityPos.getX(), blockEntityPos.getY(), blockEntityPos.getZ(), 10, false);
    }
}
