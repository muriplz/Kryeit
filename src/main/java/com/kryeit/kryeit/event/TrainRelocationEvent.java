package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface TrainRelocationEvent {
    Event<TrainRelocationEvent> EVENT = EventFactory.createArrayBacked(TrainRelocationEvent.class, listeners -> (player, train, from, to) -> {
        for (TrainRelocationEvent listener : listeners) {
            if (!listener.onTrainRelocation(player, train, from, to)) return false;
        }
        return true;
    });

    boolean onTrainRelocation(ServerPlayer player, Train train, BlockPos from, BlockPos to);
}
