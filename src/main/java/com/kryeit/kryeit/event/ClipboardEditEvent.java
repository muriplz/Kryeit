package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;
import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public interface ClipboardEditEvent {
    Event<ClipboardEditEvent> EVENT = EventFactory.createArrayBacked(ClipboardEditEvent.class, listeners -> (player, clipboard, pos) -> {
        for (ClipboardEditEvent listener : listeners) {
            if (!listener.onClipboardEdit(player, clipboard, pos)) return false;
        }
        return true;
    });

    boolean onClipboardEdit(ServerPlayer player, ClipboardBlockEntity clipboard, BlockPos pos);
}
