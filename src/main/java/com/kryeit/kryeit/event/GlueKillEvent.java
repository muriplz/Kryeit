package com.kryeit.kryeit.event;

import java.util.List;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface GlueKillEvent {
    Event<GlueKillEvent> EVENT = EventFactory.createArrayBacked(GlueKillEvent.class, listeners -> (player, blocks) -> {
        for (GlueKillEvent listener : listeners) {
            if (!listener.onKillGlue(player, blocks)) return false;
        }
        return true;
    });

    boolean onKillGlue(ServerPlayer player, List<BlockPos> blocks);
}
