package com.kryeit.kryeit.event;

import java.util.List;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface GlueCreateEvent {
    Event<GlueCreateEvent> EVENT = EventFactory.createArrayBacked(GlueCreateEvent.class, listeners -> (player, blocks) -> {
        for (GlueCreateEvent listener : listeners) {
            if (!listener.onCreateGlue(player, blocks)) return false;
        }
        return true;
    });

    boolean onCreateGlue(ServerPlayer player, List<BlockPos> blocks);
}
