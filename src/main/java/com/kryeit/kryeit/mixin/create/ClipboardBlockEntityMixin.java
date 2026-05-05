package com.kryeit.kryeit.mixin.create;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.ClipboardEditEvent;
import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.NeoForge;

@Mixin(value = ClipboardBlockEntity.class, remap = false)
public class ClipboardBlockEntityMixin {

	@Inject(method = "onEditedBy", remap = false, at = @At("HEAD"), cancellable = true)
	public void onHandle(Player player, CallbackInfo ci) {
		ClipboardBlockEntity blockEntity = (ClipboardBlockEntity) (Object) this;

		ClipboardEditEvent event = NeoForge.EVENT_BUS.post(new ClipboardEditEvent((ServerLevel) player.level(), blockEntity.getBlockPos(), blockEntity.getBlockState(), (ServerPlayer) player, blockEntity));
		if (event.isCanceled()) ci.cancel();
	}
}
