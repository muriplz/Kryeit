package com.kryeit.kryeit.mixin.create;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kryeit.kryeit.event.PotatoCannonShootEvent;
import com.simibubi.create.api.equipment.potatoCannon.PotatoCannonProjectileType;
import com.simibubi.create.content.equipment.potatoCannon.PotatoCannonItem;
import com.simibubi.create.foundation.utility.GlobalRegistryAccess;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;


@Mixin(PotatoCannonItem.class)
public class PotatoCannonItemMixin {

	@Inject(
			method = "use",
			at = @At("HEAD"),
			cancellable = true
	)
	private void onShoot(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
		ItemStack stack = player.getItemInHand(hand);
		ItemStack findAmmo = player.getProjectile(stack);

		Optional<ItemStack> ammoOptional = PotatoCannonProjectileType
				.getTypeForItem(GlobalRegistryAccess.getOrThrow(), findAmmo.getItem())
				.map($ -> findAmmo);

		if (ammoOptional.isEmpty()) return;
		ItemStack ammo = ammoOptional.get().getItem().getDefaultInstance();

		PotatoCannonShootEvent event = NeoForge.EVENT_BUS.post(new PotatoCannonShootEvent((ServerPlayer) player, ammo));
		if (event.isCanceled()) cir.setReturnValue(InteractionResultHolder.fail(stack));
	}
}
