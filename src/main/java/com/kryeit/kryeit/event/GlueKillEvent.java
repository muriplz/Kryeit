package com.kryeit.kryeit.event;

import java.util.List;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface GlueKillEvent {
    Event<GlueKillEvent> EVENT = EventFactory.createArrayBacked(GlueKillEvent.class, listeners -> (player, blocks) -> {
        for (GlueKillEvent listener : listeners) {
            return listener.onKillGlue(player, blocks);
        }
        return false;
    });

    boolean onKillGlue(ServerPlayerEntity player, List<BlockPos> blocks);
}
