package com.kryeit.kryeit.event;

import java.util.List;

import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.level.ServerPlayer;

public interface GlueCreateEvent {
    Event<GlueCreateEvent> EVENT = EventFactory.createArrayBacked(GlueCreateEvent.class, listeners -> (player, entities) -> {
        for (GlueCreateEvent listener : listeners) {
            return listener.onCreateGlue(player, entities);
        }
        return false;
    });

    boolean onCreateGlue(ServerPlayer player, List<SuperGlueEntity> entities);
}
