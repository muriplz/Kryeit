package com.kryeit.kryeit.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.level.ServerPlayer;

public interface ControlsInteractEvent {
    Event<ControlsInteractEvent> EVENT = EventFactory.createArrayBacked(ControlsInteractEvent.class, listeners -> (player, contraptionId) -> {
        for (ControlsInteractEvent listener : listeners) {
            return listener.onControlInteract(player, contraptionId);
        }
        return false;
    });

    boolean onControlInteract(ServerPlayer player, int contraptionId);
}
