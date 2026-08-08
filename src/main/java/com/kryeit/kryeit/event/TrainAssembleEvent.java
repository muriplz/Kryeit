package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface TrainAssembleEvent {
    Event<TrainAssembleEvent> EVENT = EventFactory.createArrayBacked(TrainAssembleEvent.class, listeners -> (player, train, pos) -> {
        for (TrainAssembleEvent listener : listeners) {
            if (!listener.onTrainAssembly(player, train, pos)) return false;
        }
        return true;
    });

    boolean onTrainAssembly(ServerPlayer player, Train train, BlockPos pos);
}
