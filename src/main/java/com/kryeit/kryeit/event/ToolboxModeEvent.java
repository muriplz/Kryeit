package com.kryeit.kryeit.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface ToolboxModeEvent {
    Event<ToolboxModeEvent> EVENT = EventFactory.createArrayBacked(ToolboxModeEvent.class, listeners -> (player, pos) -> {
        for (ToolboxModeEvent listener : listeners) {
            return listener.onToolboxMode(player, pos);
        }
        return false;
    });

    boolean onToolboxMode(ServerPlayer player, BlockPos pos);
}
