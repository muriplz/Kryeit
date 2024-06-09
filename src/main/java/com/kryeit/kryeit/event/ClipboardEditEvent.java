package com.kryeit.kryeit.event;

import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface ClipboardEditEvent {
    Event<ClipboardEditEvent> EVENT = EventFactory.createArrayBacked(ClipboardEditEvent.class, listeners -> (player, clipboard, pos) -> {
        for (ClipboardEditEvent listener : listeners) {
            return listener.onClipboardEdit(player, clipboard, pos);
        }
        return true;
    });

    boolean onClipboardEdit(ServerPlayerEntity player, ClipboardBlockEntity clipboard, BlockPos pos);
}
