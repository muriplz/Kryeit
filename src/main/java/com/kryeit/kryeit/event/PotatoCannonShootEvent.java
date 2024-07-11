package com.kryeit.kryeit.event;

import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface PotatoCannonShootEvent {
    Event<PotatoCannonShootEvent> EVENT = EventFactory.createArrayBacked(PotatoCannonShootEvent.class, listeners -> (player, ammo) -> {
        for (PotatoCannonShootEvent listener : listeners) {
            return listener.onCannonShoot(player, ammo);
        }
        return true;
    });

    boolean onCannonShoot(ServerPlayerEntity player, ItemStack ammo);
}
