package com.kryeit.kryeit.mixin.portinglib;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.fabricators_of_create.porting_lib.util.NetworkHooks;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(NetworkHooks.class)
public class NetworkHooksMixin {
	@Inject(method = "openScreen(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/screen/NamedScreenHandlerFactory;)V", remap = false, at = @At("HEAD"))
	private static void onHandle(ServerPlayerEntity player, NamedScreenHandlerFactory containerSupplier, CallbackInfo ci) {
		System.out.println("Hehe noice");
	}
}
