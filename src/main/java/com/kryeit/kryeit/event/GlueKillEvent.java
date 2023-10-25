package com.kryeit.kryeit.event;

import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public interface GlueKillEvent {
    Event<GlueKillEvent> EVENT = EventFactory.createArrayBacked(GlueKillEvent.class, listeners -> (player, entities) -> {
        for (GlueKillEvent listener : listeners) {
            return listener.onKillGlue(player, entities);
        }
        return false;
    });

    boolean onKillGlue(ServerPlayer player, List<SuperGlueEntity> entities);
}
