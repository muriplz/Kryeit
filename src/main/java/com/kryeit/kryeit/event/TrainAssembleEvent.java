package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface TrainAssembleEvent {
    Event<TrainAssembleEvent> EVENT = EventFactory.createArrayBacked(TrainAssembleEvent.class, listeners -> (player, train, pos) -> {
        for (TrainAssembleEvent listener : listeners) {
            return listener.onTrainAssembly(player, train, pos);
        }
        return true;
    });

    boolean onTrainAssembly(ServerPlayerEntity player, Train train, BlockPos pos);
}
