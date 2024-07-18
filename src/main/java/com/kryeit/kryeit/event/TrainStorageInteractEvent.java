package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface TrainStorageInteractEvent {
    Event<TrainStorageInteractEvent> EVENT = EventFactory.createArrayBacked(TrainStorageInteractEvent.class, listeners -> (player, train, pos) -> {
        for (TrainStorageInteractEvent listener : listeners) {
            return listener.onTrainStorageInteract(player, train, pos);
        }
        return true;
    });

    boolean onTrainStorageInteract(ServerPlayerEntity player, Train train, BlockPos pos);
}
