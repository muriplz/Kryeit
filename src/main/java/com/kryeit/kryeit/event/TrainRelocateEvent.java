package com.kryeit.kryeit.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface TrainRelocateEvent {
    Event<TrainRelocateEvent> EVENT = EventFactory.createArrayBacked(TrainRelocateEvent.class, listeners -> (player, entityId, pos) -> {
        for (TrainRelocateEvent listener : listeners) {
            return listener.onRelocation(player, entityId, pos);
        }
        return false;
    });

    boolean onRelocation(ServerPlayer player, int entityId, BlockPos pos);
}
