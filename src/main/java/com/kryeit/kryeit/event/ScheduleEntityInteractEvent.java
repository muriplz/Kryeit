package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface ScheduleEntityInteractEvent {
    Event<ScheduleEntityInteractEvent> EVENT = EventFactory.createArrayBacked(ScheduleEntityInteractEvent.class, listeners -> (player, train, seatPos) -> {
        for (ScheduleEntityInteractEvent listener : listeners) {
            if (!listener.onScheduleEntityInteract(player, train, seatPos)) return false;
        }
        return true;
    });

    boolean onScheduleEntityInteract(ServerPlayer player, Train train, BlockPos seatPos);
}
