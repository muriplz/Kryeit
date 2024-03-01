package com.kryeit.kryeit.listener;

import com.kryeit.kryeit.MinecraftServerSupplier;
import com.kryeit.kryeit.event.TrainRelocationEvent;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class OnTrainRelocate implements TrainRelocationEvent {
	@Override
	public boolean onTrainRelocation(ServerPlayer player, Train train) {
		return true;
	}
}
