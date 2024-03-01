package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface ControlsInteractionEvent {
    Event<ControlsInteractionEvent> EVENT = EventFactory.createArrayBacked(ControlsInteractionEvent.class, listeners -> (player, train, trainPos) -> {
        for (ControlsInteractionEvent listener : listeners) {
            return listener.onControlsInteraction(player, train, trainPos);
        }
        return false;
    });

    boolean onControlsInteraction(ServerPlayer player, Train train, BlockPos trainPos);
}
