package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface TrainChangeNameEvent {
    Event<TrainChangeNameEvent> EVENT = EventFactory.createArrayBacked(TrainChangeNameEvent.class, listeners -> (player, train, pos) -> {
        for (TrainChangeNameEvent listener : listeners) {
            if (!listener.onTrainChangeName(player, train, pos)) return false;
        }
        return true;
    });

    boolean onTrainChangeName(ServerPlayer player, Train train, BlockPos pos);
}
