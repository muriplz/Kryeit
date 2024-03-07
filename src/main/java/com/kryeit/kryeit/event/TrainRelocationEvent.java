package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface TrainRelocationEvent {
    Event<TrainRelocationEvent> EVENT = EventFactory.createArrayBacked(TrainRelocationEvent.class, listeners -> (player, train, trainPos) -> {
        for (TrainRelocationEvent listener : listeners) {
            return listener.onTrainRelocation(player, train, trainPos);
        }
        return false;
    });

    boolean onTrainRelocation(ServerPlayerEntity player, Train train, BlockPos trainPos);
}
