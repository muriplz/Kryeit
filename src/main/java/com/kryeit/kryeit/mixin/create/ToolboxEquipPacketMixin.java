package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.Main;
import com.kryeit.kryeit.event.ToolboxEquipEvent;
import com.simibubi.create.content.equipment.toolbox.ToolboxEquipPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

@Mixin(ToolboxEquipPacket.class)
public class ToolboxEquipPacketMixin {
	@Final
	@Shadow
	private BlockPos toolboxPos;

	@Inject(method = "handle", remap = false, at = @At("HEAD"), cancellable = true)
	public void onHandle(ServerPlayer player, CallbackInfo ci) {
		ToolboxEquipEvent event = Main.MOD_BUS.post(new ToolboxEquipEvent(player, toolboxPos));
		if (event.isCanceled()) ci.cancel();
	}

}
