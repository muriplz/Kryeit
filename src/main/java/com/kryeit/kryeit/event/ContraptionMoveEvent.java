package com.kryeit.kryeit.event;

import com.simibubi.create.content.contraptions.Contraption;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ContraptionMoveEvent {
    Event<ContraptionMoveEvent> EVENT = EventFactory.createArrayBacked(ContraptionMoveEvent.class, listeners -> (contraption) -> {
        for (ContraptionMoveEvent listener : listeners) {
            return listener.onContraptionMove(contraption);
        }
        return false;
    });

    boolean onContraptionMove(Contraption contraption);
}
