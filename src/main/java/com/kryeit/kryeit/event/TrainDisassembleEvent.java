package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface TrainDisassembleEvent {
    Event<TrainDisassembleEvent> EVENT = EventFactory.createArrayBacked(TrainDisassembleEvent.class, listeners -> (player, train, pos) -> {
        for (TrainDisassembleEvent listener : listeners) {
            if (!listener.onTrainDisassembly(player, train, pos)) return false;
        }
        return true;
    });

    boolean onTrainDisassembly(ServerPlayer player, Train train, BlockPos pos);
}
