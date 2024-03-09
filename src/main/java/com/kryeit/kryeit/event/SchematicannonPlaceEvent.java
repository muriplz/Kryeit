package com.kryeit.kryeit.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface SchematicannonPlaceEvent {
    Event<SchematicannonPlaceEvent> EVENT = EventFactory.createArrayBacked(SchematicannonPlaceEvent.class, listeners -> (player, blockEntity, blockPos) -> {
        for (SchematicannonPlaceEvent listener : listeners) {
            return listener.onBlockPlaced(player, blockEntity, blockPos);
        }
        return false;
    });

    boolean onBlockPlaced(ServerPlayerEntity player, BlockEntity blockEntity, BlockPos blockPos);
}
