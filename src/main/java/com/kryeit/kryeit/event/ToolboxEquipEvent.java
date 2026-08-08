package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface ToolboxEquipEvent {
    Event<ToolboxEquipEvent> EVENT = EventFactory.createArrayBacked(ToolboxEquipEvent.class, listeners -> (player, toolboxPos) -> {
        for (ToolboxEquipEvent listener : listeners) {
            if (!listener.onToolboxEquip(player, toolboxPos)) return false;
        }
        return true;
    });

    boolean onToolboxEquip(ServerPlayer player, BlockPos toolboxPos);
}
