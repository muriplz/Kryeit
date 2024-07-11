package com.kryeit.kryeit.listener;

import com.kryeit.kryeit.event.ClipboardEditEvent;
import com.kryeit.kryeit.event.PotatoCannonShootEvent;
import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;

public class OnPotatoCannonShoot implements PotatoCannonShootEvent {

	@Override
	public boolean onCannonShoot(ServerPlayerEntity player, ItemStack ammo) {

		List<String> bannedAmmo = Arrays.asList(
				"item.create.blaze_cake",
				"item.minecraft.baked_potato"
		);

		String key = ammo.getTranslationKey();
        return !bannedAmmo.contains(key);
    }
}
