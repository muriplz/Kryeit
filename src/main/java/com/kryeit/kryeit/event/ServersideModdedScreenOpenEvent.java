package com.kryeit.kryeit.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface ServersideModdedScreenOpenEvent {
    Event<ServersideModdedScreenOpenEvent> EVENT = EventFactory.createArrayBacked(ServersideModdedScreenOpenEvent.class, listeners -> (player, blockEntity) -> {
        for (ServersideModdedScreenOpenEvent listener : listeners) {
            return listener.onGUIOpen(player, blockEntity);
        }
        return false;
    });

    boolean onGUIOpen(ServerPlayerEntity player, BlockEntity blockEntity);
}
