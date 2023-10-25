package com.kryeit.kryeit.event;

import com.simibubi.create.content.contraptions.Contraption;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;

public interface ContraptionInteractEvent {
    Event<ContraptionInteractEvent> EVENT = EventFactory.createArrayBacked(ContraptionInteractEvent.class, listeners -> (player, contraption) -> {
        for (ContraptionInteractEvent listener : listeners) {
            return listener.onContraptionInteract(player, contraption);
        }
        return false;
    });

    boolean onContraptionInteract(Player player, Contraption contraption);
}
