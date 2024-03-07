package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface ControlsInteractionEvent {
    Event<ControlsInteractionEvent> EVENT = EventFactory.createArrayBacked(ControlsInteractionEvent.class, listeners -> (player, train, trainPos) -> {
        for (ControlsInteractionEvent listener : listeners) {
            return listener.onControlsInteraction(player, train, trainPos);
        }
        return false;
    });

    boolean onControlsInteraction(ServerPlayerEntity player, Train train, BlockPos trainPos);
}
