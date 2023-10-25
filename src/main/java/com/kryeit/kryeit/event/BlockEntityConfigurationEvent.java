package com.kryeit.kryeit.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

public interface BlockEntityConfigurationEvent {
    Event<BlockEntityConfigurationEvent> EVENT = EventFactory.createArrayBacked(BlockEntityConfigurationEvent.class, listeners -> (player, pos) -> {
        for (BlockEntityConfigurationEvent listener : listeners) {
            return listener.onConfigurationChange(player, pos);
        }
        return false;
    });

    boolean onConfigurationChange(Player player, BlockPos pos);
}
