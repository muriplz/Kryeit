package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface TrainDisassembleEvent {
    Event<TrainDisassembleEvent> EVENT = EventFactory.createArrayBacked(TrainDisassembleEvent.class, listeners -> (player, train, pos) -> {
        for (TrainDisassembleEvent listener : listeners) {
            return listener.onTrainDisassembly(player, train, pos);
        }
        return true;
    });

    boolean onTrainDisassembly(ServerPlayerEntity player, Train train, BlockPos pos);
}
