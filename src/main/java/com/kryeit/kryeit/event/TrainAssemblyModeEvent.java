package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface TrainAssemblyModeEvent {
    Event<TrainAssemblyModeEvent> EVENT = EventFactory.createArrayBacked(TrainAssemblyModeEvent.class, listeners -> (player, train, pos) -> {
        for (TrainAssemblyModeEvent listener : listeners) {
            return listener.onTrainAssembly(player, train, pos);
        }
        return true;
    });

    boolean onTrainAssembly(ServerPlayerEntity player, Train train, BlockPos pos);
}
