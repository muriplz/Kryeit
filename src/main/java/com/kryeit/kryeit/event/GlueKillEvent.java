package com.kryeit.kryeit.event;

import java.util.List;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface GlueKillEvent {
    Event<GlueKillEvent> EVENT = EventFactory.createArrayBacked(GlueKillEvent.class, listeners -> (player, blocks) -> {
        for (GlueKillEvent listener : listeners) {
            return listener.onKillGlue(player, blocks);
        }
        return false;
    });

    boolean onKillGlue(ServerPlayer player, List<BlockPos> blocks);
}
