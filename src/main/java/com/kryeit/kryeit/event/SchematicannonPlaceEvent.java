package com.kryeit.kryeit.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface SchematicannonPlaceEvent {
    Event<SchematicannonPlaceEvent> EVENT = EventFactory.createArrayBacked(SchematicannonPlaceEvent.class, listeners -> (cannon, target) -> {
        for (SchematicannonPlaceEvent listener : listeners) {
            return listener.onCannonPlace(cannon, target);
        }
        return false;
    });

    boolean onCannonPlace(BlockEntity cannon, BlockPos target);
}
