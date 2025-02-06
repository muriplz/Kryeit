package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface TrainChangeNameEvent {
    Event<TrainChangeNameEvent> EVENT = EventFactory.createArrayBacked(TrainChangeNameEvent.class, listeners -> (player, train, pos) -> {
        for (TrainChangeNameEvent listener : listeners) {
            return listener.onTrainChangeName(player, train, pos);
        }
        return true;
    });

    boolean onTrainChangeName(ServerPlayerEntity player, Train train, BlockPos pos);
}
