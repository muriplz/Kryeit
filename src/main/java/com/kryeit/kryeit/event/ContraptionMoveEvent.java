package com.kryeit.kryeit.event;

import com.simibubi.create.content.contraptions.Contraption;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.level.Level;

public interface ContraptionMoveEvent {
    Event<ContraptionMoveEvent> EVENT = EventFactory.createArrayBacked(ContraptionMoveEvent.class, listeners -> (level, contraption) -> {
        for (ContraptionMoveEvent listener : listeners) {
            return listener.onContraptionMove(level, contraption);
        }
        return false;
    });

    boolean onContraptionMove(Level level, Contraption contraption);
}
