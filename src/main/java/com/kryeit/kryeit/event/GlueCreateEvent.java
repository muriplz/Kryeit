package com.kryeit.kryeit.event;

import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public interface GlueCreateEvent {
    Event<GlueCreateEvent> EVENT = EventFactory.createArrayBacked(GlueCreateEvent.class, listeners -> (player, entities) -> {
        for (GlueCreateEvent listener : listeners) {
            return listener.onCreateGlue(player, entities);
        }
        return false;
    });

    boolean onCreateGlue(ServerPlayer player, List<SuperGlueEntity> entities);
}
