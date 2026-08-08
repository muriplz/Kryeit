package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;
import com.simibubi.create.content.trains.entity.Train;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface ControlsInteractEvent {
    Event<ControlsInteractEvent> EVENT = EventFactory.createArrayBacked(ControlsInteractEvent.class, listeners -> (player, train, controlsPos) -> {
        for (ControlsInteractEvent listener : listeners) {
            if (!listener.onControlsInteract(player, train, controlsPos)) return false;
        }
        return true;
    });

    boolean onControlsInteract(ServerPlayer player, Train train, BlockPos controlsPos);
}
