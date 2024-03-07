package com.kryeit.kryeit.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface ToolboxEquipEvent {
    Event<ToolboxEquipEvent> EVENT = EventFactory.createArrayBacked(ToolboxEquipEvent.class, listeners -> (player, toolboxPos) -> {
        for (ToolboxEquipEvent listener : listeners) {
            return listener.onToolboxEquip(player, toolboxPos);
        }
        return false;
    });

    boolean onToolboxEquip(ServerPlayerEntity player, BlockPos toolboxPos);
}
