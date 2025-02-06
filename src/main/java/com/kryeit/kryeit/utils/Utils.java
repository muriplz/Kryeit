package com.kryeit.kryeit.utils;

import java.util.List;
import java.util.UUID;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.TrustTypes;
import com.kryeit.kryeit.MinecraftServerSupplier;

import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Utils {

	public static UUID getClaimOwner(List<BlockPos> blocks) {
		Claim claim;

		for (BlockPos block : blocks) {
			claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(MinecraftServerSupplier.getServer().getOverworld()), block.getX(), block.getY(), block.getZ());
			if (claim == null || claim.isWilderness()) continue;

			return claim.getOwnerUniqueId();
		}
		return null;
	}
	public static boolean isWilderness(List<BlockPos> blocks) {
		Claim claim;

		for (BlockPos block : blocks) {
			claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(MinecraftServerSupplier.getServer().getOverworld()), block.getX(), block.getY(), block.getZ());
			if (claim == null || claim.isWilderness()) continue;

			return false;
		}
		return true;
	}

	public static boolean canBreakBlocks(ServerPlayerEntity player, List<BlockPos> blocks) {
		Claim claim;

		for (BlockPos block : blocks) {
			claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(player.getWorld()), block.getX(), block.getY(), block.getZ());
			if (claim == null || claim.isWilderness()) continue;

			if (!hasTrustInBlock(player, block)) {
				return false;
			}
		}
		return true;
	}

	public static boolean hasTrustInBlock(ServerPlayerEntity player, BlockPos block) {
		Claim claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(player.getWorld()), block.getX(), block.getY(), block.getZ());
		if (claim == null || claim.isWilderness()) return true;

		return claim.isUserTrusted(player.getUuid(), TrustTypes.BUILDER);
	}
	public static void broadcast(String s) {
		MinecraftServerSupplier.getServer().getPlayerManager().broadcast(
				Text.literal(s),
				false
		);
	}

    public static ServerPlayerEntity getClosestPlayer(BlockPos blockEntityPos, RegistryKey<World> blockEntityDimension) {
		return (ServerPlayerEntity) MinecraftServerSupplier.getServer().getWorld(blockEntityDimension).getClosestPlayer(blockEntityPos.getX(), blockEntityPos.getY(), blockEntityPos.getZ(), 10, false);
    }
}
