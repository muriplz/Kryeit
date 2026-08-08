package com.kryeit.kryeit.event;

import com.kryeit.kryeit.event.api.Event;
import com.kryeit.kryeit.event.api.EventFactory;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public interface PotatoCannonShootEvent {
    Event<PotatoCannonShootEvent> EVENT = EventFactory.createArrayBacked(PotatoCannonShootEvent.class, listeners -> (player, ammo) -> {
        for (PotatoCannonShootEvent listener : listeners) {
            if (!listener.onCannonShoot(player, ammo)) return false;
        }
        return true;
    });

    boolean onCannonShoot(ServerPlayer player, ItemStack ammo);
}
