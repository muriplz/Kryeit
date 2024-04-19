package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.ClipboardEditEvent;
import com.simibubi.create.content.equipment.clipboard.ClipboardBlockEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

@Mixin(value = ClipboardBlockEntity.class, remap = false)
public class ClipboardBlockEntityMixin {

	@Inject(method = "onEditedBy", remap = false, at = @At("HEAD"), cancellable = true)
	public void onHandle(PlayerEntity player, CallbackInfo ci){
		ClipboardBlockEntity blockEntity = (ClipboardBlockEntity) (Object) this;

		BlockPos pos = blockEntity.getPos();

		if (!ClipboardEditEvent.EVENT.invoker().onClipboardEdit((ServerPlayerEntity) player, blockEntity, pos)) {
			ci.cancel();
		}
	}

}
