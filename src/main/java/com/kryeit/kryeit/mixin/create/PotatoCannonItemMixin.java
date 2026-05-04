package com.kryeit.kryeit.mixin.create;

import java.util.Optional;

import com.simibubi.create.api.equipment.potatoCannon.PotatoCannonProjectileType;

import com.simibubi.create.foundation.utility.GlobalRegistryAccess;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kryeit.kryeit.event.PotatoCannonShootEvent;
import com.simibubi.create.content.equipment.potatoCannon.PotatoCannonItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

@Mixin(PotatoCannonItem.class)
public class PotatoCannonItemMixin {

	@Inject(
			method = "use",
			at = @At("HEAD"),
			cancellable = true
	)
	private void onShoot(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
		ItemStack stack = player.getStackInHand(hand);
		ItemStack findAmmo = player.getProjectileType(stack);

		Optional<ItemStack> ammoOptional = PotatoCannonProjectileType
				.getTypeForItem(GlobalRegistryAccess.getOrThrow(), findAmmo.getItem())
				.map($ -> findAmmo);

		if (ammoOptional.isEmpty()) return;
		ItemStack ammo = ammoOptional.get().getItem().getDefaultStack();

		if (!PotatoCannonShootEvent.EVENT.invoker().onCannonShoot((ServerPlayerEntity) player, ammo)) {
			cir.setReturnValue(TypedActionResult.fail(stack));
		}
	}
}
