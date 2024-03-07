package com.kryeit.kryeit.event;

import com.simibubi.create.content.trains.entity.Train;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface ToolboxEquipEvent {
    Event<ToolboxEquipEvent> EVENT = EventFactory.createArrayBacked(ToolboxEquipEvent.class, listeners -> (player, toolboxPos) -> {
        for (ToolboxEquipEvent listener : listeners) {
            return listener.onToolboxEquip(player, toolboxPos);
        }
        return false;
    });

    boolean onToolboxEquip(ServerPlayer player, BlockPos toolboxPos);
}
