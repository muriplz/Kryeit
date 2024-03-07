package com.kryeit.kryeit.event;

import java.util.List;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface GlueCreateEvent {
    Event<GlueCreateEvent> EVENT = EventFactory.createArrayBacked(GlueCreateEvent.class, listeners -> (player, blocks) -> {
        for (GlueCreateEvent listener : listeners) {
            return listener.onCreateGlue(player, blocks);
        }
        return false;
    });

    boolean onCreateGlue(ServerPlayerEntity player, List<BlockPos> blocks);
}
