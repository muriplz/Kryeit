package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface ControlsInteractEvent {
    Event<ControlsInteractEvent> EVENT = EventFactory.createArrayBacked(ControlsInteractEvent.class, listeners -> (player, train, controlsPos) -> {
        for (ControlsInteractEvent listener : listeners) {
            return listener.onControlsInteract(player, train, controlsPos);
        }
        return true;
    });

    boolean onControlsInteract(ServerPlayerEntity player, Train train, BlockPos controlsPos);
}
