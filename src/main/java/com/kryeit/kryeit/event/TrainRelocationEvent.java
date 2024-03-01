package com.kryeit.kryeit.event;

import java.util.List;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface TrainRelocationEvent {
    Event<TrainRelocationEvent> EVENT = EventFactory.createArrayBacked(TrainRelocationEvent.class, listeners -> (player, train) -> {
        for (TrainRelocationEvent listener : listeners) {
            return listener.onTrainRelocation(player, train);
        }
        return false;
    });

    boolean onTrainRelocation(ServerPlayer player, Train train);
}
