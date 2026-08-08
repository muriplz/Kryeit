package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.ClipboardEditEvent;
import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

@Mixin(value = ClipboardBlockEntity.class, remap = false)
public class ClipboardBlockEntityMixin {

	@Inject(method = "onEditedBy", at = @At("HEAD"), cancellable = true)
	private void kryeit$onHandle(Player player, CallbackInfo ci) {
		ClipboardBlockEntity blockEntity = (ClipboardBlockEntity) (Object) this;

		BlockPos pos = blockEntity.getBlockPos();

		if (!(player instanceof ServerPlayer serverPlayer))
			return;

		if (!ClipboardEditEvent.EVENT.invoker().onClipboardEdit(serverPlayer, blockEntity, pos)) {
			ci.cancel();
		}
	}
}
