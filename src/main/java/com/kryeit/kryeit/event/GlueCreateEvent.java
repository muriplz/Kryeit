package com.kryeit.kryeit.event;

import java.util.List;

import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.level.ServerPlayer;

public interface GlueCreateEvent {
    Event<GlueCreateEvent> EVENT = EventFactory.createArrayBacked(GlueCreateEvent.class, listeners -> (player, entity) -> {
        for (GlueCreateEvent listener : listeners) {
            return listener.onCreateGlue(player, entity);
        }
        return false;
    });

    boolean onCreateGlue(ServerPlayer player, SuperGlueEntity entity);
}
