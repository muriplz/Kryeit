package com.kryeit.kryeit.event;

import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.level.ServerPlayer;

public interface GlueKillEvent {
    Event<GlueKillEvent> EVENT = EventFactory.createArrayBacked(GlueKillEvent.class, listeners -> (player, entity) -> {
        for (GlueKillEvent listener : listeners) {
            return listener.onKillGlue(player, entity);
        }
        return false;
    });

    boolean onKillGlue(ServerPlayer player, SuperGlueEntity entity);
}
