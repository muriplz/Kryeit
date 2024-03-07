package com.kryeit.kryeit.compat;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class GriefDefenderImpl {

	public static List<BlockPos> getBlockPositionsInCube(BlockPos from, BlockPos to) {
		List<BlockPos> blockPositions = new ArrayList<>();
		int startX = Math.min(from.getX(), to.getX());
		int startY = Math.min(from.getY(), to.getY());
		int startZ = Math.min(from.getZ(), to.getZ());
		int endX = Math.max(from.getX(), to.getX());
		int endY = Math.max(from.getY(), to.getY());
		int endZ = Math.max(from.getZ(), to.getZ());

		for (int x = startX; x <= endX; x++) {
			for (int y = startY; y <= endY; y++) {
				for (int z = startZ; z <= endZ; z++) {
					blockPositions.add(new BlockPos(x, y, z));
				}
			}
		}

		return blockPositions;
	}

	public static List<BlockPos> getBlockPositionsInAABB(Box aabb) {
		List<BlockPos> blockPositions = new ArrayList<>();
		// Explicitly cast the double values to int, flooring them in the process
		BlockPos min = new BlockPos((int) Math.round(aabb.minX), (int) Math.round(aabb.minY), (int) Math.round(aabb.minZ));
		BlockPos max = new BlockPos((int) Math.round(aabb.maxX), (int) Math.round(aabb.maxY), (int) Math.round(aabb.maxZ));

		// Iterate through all positions within the AABB, from min to max, inclusive
		for (int x = min.getX(); x <= max.getX(); x++) {
			for (int y = min.getY(); y <= max.getY(); y++) {
				for (int z = min.getZ(); z <= max.getZ(); z++) {
					blockPositions.add(new BlockPos(x, y, z));
				}
			}
		}

		return blockPositions;
	}

}
