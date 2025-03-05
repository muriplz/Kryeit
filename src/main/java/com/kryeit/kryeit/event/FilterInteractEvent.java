package com.kryeit.kryeit.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface FilterInteractEvent {
    Event<FilterInteractEvent> EVENT = EventFactory.createArrayBacked(FilterInteractEvent.class, listeners -> (player, pos) -> {
        for (FilterInteractEvent listener : listeners) {
            return listener.onFilterInteract(player, pos);
        }
        return true;
    });

    boolean onFilterInteract(ServerPlayerEntity player, BlockPos pos);
}
