package com.kryeit.kryeit.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface ToolboxPickupEvent {
    Event<ToolboxPickupEvent> EVENT = EventFactory.createArrayBacked(ToolboxPickupEvent.class, listeners -> (player, toolboxPos) -> {
        for (ToolboxPickupEvent listener : listeners) {
            return listener.onToolboxPickup(player, toolboxPos);
        }
        return false;
    });

    boolean onToolboxPickup(ServerPlayerEntity player, BlockPos toolboxPos);
}
