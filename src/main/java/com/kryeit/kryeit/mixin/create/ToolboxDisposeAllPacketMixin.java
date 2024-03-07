package com.kryeit.kryeit.mixin.create;

import com.kryeit.kryeit.event.ToolboxDisposeAllEvent;
import com.simibubi.create.content.equipment.toolbox.ToolboxDisposeAllPacket;

import com.simibubi.create.foundation.networking.SimplePacketBase;

import net.minecraft.core.BlockPos;

import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToolboxDisposeAllPacket.class)
public class ToolboxDisposeAllPacketMixin {

	@Shadow
	private BlockPos toolboxPos;

	@Inject(method = "lambda$handle$1", remap = false, at = @At("HEAD"), cancellable = true)
	public void onHandle(SimplePacketBase.Context context, CallbackInfo ci){
		ServerPlayer player = context.getSender();
		if (player == null)
			return;
		if(!ToolboxDisposeAllEvent.EVENT.invoker().onToolboxDispose(player, toolboxPos))
			ci.cancel();
	}

}
