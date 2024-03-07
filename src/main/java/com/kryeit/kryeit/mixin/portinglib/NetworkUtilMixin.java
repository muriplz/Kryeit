package com.kryeit.kryeit.mixin.portinglib;

import com.simibubi.create.content.contraptions.glue.SuperGlueRemovalPacket;

import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.github.fabricators_of_create.porting_lib.util.NetworkHooks;

@Mixin(NetworkHooks.class)
public class NetworkUtilMixin {
	@Inject(method = "openScreen(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/screen/NamedScreenHandlerFactory;)V", remap = false, at = @At("HEAD"), cancellable = true)
	private static void onHandle(ServerPlayerEntity player, NamedScreenHandlerFactory containerSupplier, CallbackInfo ci) {
		System.out.println("Hehe noice");
	}
}
