package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface TrainDisassemblyEvent {
    Event<TrainDisassemblyEvent> EVENT = EventFactory.createArrayBacked(TrainDisassemblyEvent.class, listeners -> (player, train, pos) -> {
        for (TrainDisassemblyEvent listener : listeners) {
            return listener.onTrainDisassembly(player, train, pos);
        }
        return false;
    });

    boolean onTrainDisassembly(ServerPlayerEntity player, Train train, BlockPos pos);
}
