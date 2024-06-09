package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface ScheduleEntityInteractEvent {
    Event<ScheduleEntityInteractEvent> EVENT = EventFactory.createArrayBacked(ScheduleEntityInteractEvent.class, listeners -> (player, train, seatPos) -> {
        for (ScheduleEntityInteractEvent listener : listeners) {
            return listener.onScheduleEntityInteract(player, train, seatPos);
        }
        return true;
    });

    boolean onScheduleEntityInteract(ServerPlayerEntity player, Train train, BlockPos seatPos);
}
