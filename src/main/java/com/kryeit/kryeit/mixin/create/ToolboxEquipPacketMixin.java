package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.ToolboxEquipEvent;
import com.simibubi.create.content.equipment.toolbox.ToolboxEquipPacket;
import com.simibubi.create.foundation.networking.SimplePacketBase;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

@Mixin(ToolboxEquipPacket.class)
public class ToolboxEquipPacketMixin {

	@Shadow
	private BlockPos toolboxPos;

	@Inject(method = "lambda$handle$1", remap = false, at = @At("HEAD"), cancellable = true)
	public void onHandle(SimplePacketBase.Context context, CallbackInfo ci){
		ServerPlayerEntity player = context.getSender();
		if (player == null)
			return;
		if(!ToolboxEquipEvent.EVENT.invoker().onToolboxEquip(player, toolboxPos))
			ci.cancel();
	}

}
