package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.ToolboxEquipEvent;
import com.simibubi.create.content.equipment.toolbox.ToolboxEquipPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

@Mixin(value = ToolboxEquipPacket.class, remap = false)
public class ToolboxEquipPacketMixin {

	@Shadow
	private BlockPos toolboxPos;

	@Inject(method = "handle", at = @At("HEAD"), cancellable = true)
	private void kryeit$onHandle(ServerPlayer player, CallbackInfo ci) {
		if (player == null)
			return;
		if (!ToolboxEquipEvent.EVENT.invoker().onToolboxEquip(player, toolboxPos)) {
			ci.cancel();
		}
	}
}
