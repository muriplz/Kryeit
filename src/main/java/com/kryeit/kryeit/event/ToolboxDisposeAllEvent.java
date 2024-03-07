package com.kryeit.kryeit.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface ToolboxDisposeAllEvent {
    Event<ToolboxDisposeAllEvent> EVENT = EventFactory.createArrayBacked(ToolboxDisposeAllEvent.class, listeners -> (player, toolboxPos) -> {
        for (ToolboxDisposeAllEvent listener : listeners) {
            return listener.onToolboxDispose(player, toolboxPos);
        }
        return false;
    });

    boolean onToolboxDispose(ServerPlayer player, BlockPos toolboxPos);
}
