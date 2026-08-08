package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface FilterInteractEvent {
    Event<FilterInteractEvent> EVENT = EventFactory.createArrayBacked(FilterInteractEvent.class, listeners -> (player, pos) -> {
        for (FilterInteractEvent listener : listeners) {
            if (!listener.onFilterInteract(player, pos)) return false;
        }
        return true;
    });

    boolean onFilterInteract(ServerPlayer player, BlockPos pos);
}
