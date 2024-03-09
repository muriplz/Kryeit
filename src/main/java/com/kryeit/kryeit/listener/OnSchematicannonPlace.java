package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.SchematicannonPlaceEvent;
import com.kryeit.kryeit.event.ServersideModdedScreenOpenEvent;
import com.kryeit.kryeit.utils.Utils;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;

public class OnSchematicannonPlace implements SchematicannonPlaceEvent {

	@Override
	public boolean onBlockPlaced(ServerPlayerEntity player, BlockEntity blockEntity, BlockPos blockPos) {
		return Utils.canBreakBlocks(player, List.of(blockPos));
	}
}
