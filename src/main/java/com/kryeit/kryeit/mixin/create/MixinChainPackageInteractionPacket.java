package com.kryeit.kryeit.mixin.create;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.utils.Utils;
import com.simibubi.create.content.kinetics.chainConveyor.ChainConveyorBlockEntity;
import com.simibubi.create.content.kinetics.chainConveyor.ChainPackageInteractionPacket;

import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ChainPackageInteractionPacket.class)
public class MixinChainPackageInteractionPacket {
	@Shadow
	private boolean removingPackage;

	@Inject(method = "applySettings(Lnet/minecraft/server/network/ServerPlayerEntity;Lcom/simibubi/create/content/kinetics/chainConveyor/ChainConveyorBlockEntity;)V", at = @At("HEAD"), cancellable = true)
	public void checkApplySettings(ServerPlayerEntity player, ChainConveyorBlockEntity be, CallbackInfo ci) {
		if (!Utils.canBreakBlocks(player, List.of(be.getPos()))) {
			ci.cancel();
		}
	}
}
