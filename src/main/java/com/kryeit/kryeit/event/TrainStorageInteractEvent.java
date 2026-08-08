package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface TrainStorageInteractEvent {
    Event<TrainStorageInteractEvent> EVENT = EventFactory.createArrayBacked(TrainStorageInteractEvent.class, listeners -> (player, train, pos) -> {
        for (TrainStorageInteractEvent listener : listeners) {
            if (!listener.onTrainStorageInteract(player, train, pos)) return false;
        }
        return true;
    });

    boolean onTrainStorageInteract(ServerPlayer player, Train train, BlockPos pos);
}
