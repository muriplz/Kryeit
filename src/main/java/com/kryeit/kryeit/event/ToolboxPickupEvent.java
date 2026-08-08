package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface ToolboxPickupEvent {
    Event<ToolboxPickupEvent> EVENT = EventFactory.createArrayBacked(ToolboxPickupEvent.class, listeners -> (player, toolboxPos) -> {
        for (ToolboxPickupEvent listener : listeners) {
            if (!listener.onToolboxPickup(player, toolboxPos)) return false;
        }
        return true;
    });

    boolean onToolboxPickup(ServerPlayer player, BlockPos toolboxPos);
}
