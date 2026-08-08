package com.kryeit.kryeit.utils;

import java.util.List;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.TrustTypes;
import com.kryeit.kryeit.MinecraftServerSupplier;
import com.kryeit.kryeit.compat.CompatAddon;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Utils {

	public static boolean isWilderness(ServerPlayer player, List<BlockPos> blocks) {
		Claim claim;

		for (BlockPos block : blocks) {
			claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(player.serverLevel()), block.getX(), block.getY(), block.getZ());
			if (claim == null || claim.isWilderness()) continue;

			return false;
		}
		return true;
	}

	public static boolean canBreakBlocks(ServerPlayer player, List<BlockPos> blocks) {
		Claim claim;

		for (BlockPos block : blocks) {
			claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(player.serverLevel()), block.getX(), block.getY(), block.getZ());
			if (claim == null || claim.isWilderness()) continue;

			if (!hasTrustInBlock(player, block)) {
				return false;
			}
		}
		return true;
	}

	public static boolean hasTrustInBlock(ServerPlayer player, BlockPos block) {
		Claim claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(player.serverLevel()), block.getX(), block.getY(), block.getZ());
		if (claim == null || claim.isWilderness()) return true;

		return claim.isUserTrusted(player.getUUID(), TrustTypes.BUILDER);
	}

	public static void broadcast(String s) {
		MinecraftServerSupplier.getServer().getPlayerList().broadcastSystemMessage(Component.literal(s), false);
	}

	public static ServerPlayer getClosestPlayer(BlockPos blockEntityPos, ResourceKey<Level> blockEntityDimension) {
		ServerLevel level = MinecraftServerSupplier.getServer().getLevel(blockEntityDimension);
		if (level == null) return null;
		Player player = level.getNearestPlayer(blockEntityPos.getX(), blockEntityPos.getY(), blockEntityPos.getZ(), 10, false);
		return player instanceof ServerPlayer serverPlayer ? serverPlayer : null;
	}

	/**
	 * Staff bypass. Replaces the fabric-permissions-api {@code Permissions.check(player, node)}
	 * used on Fabric with a direct LuckPerms lookup; returns false when LuckPerms is absent.
	 */
	public static boolean hasPermission(ServerPlayer player, String node) {
		if (!CompatAddon.LUCKPERMS.isLoaded()) return false;
		try {
			LuckPerms api = LuckPermsProvider.get();
			User user = api.getUserManager().getUser(player.getUUID());
			if (user == null) return false;
			return user.getCachedData()
					.getPermissionData(QueryOptions.defaultContextualOptions())
					.checkPermission(node)
					.asBoolean();
		} catch (Throwable ignored) {
			return false;
		}
	}
}
