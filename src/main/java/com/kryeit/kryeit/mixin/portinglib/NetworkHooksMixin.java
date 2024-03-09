package com.kryeit.kryeit.mixin.portinglib;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.ServersideModdedScreenOpenEvent;
import com.simibubi.create.content.schematics.cannon.SchematicannonBlockEntity;

import io.github.fabricators_of_create.porting_lib.util.NetworkHooks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(NetworkHooks.class)
public class NetworkHooksMixin {

	@Inject(method = "openScreen(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/screen/NamedScreenHandlerFactory;Ljava/util/function/Consumer;)V", remap = false, at = @At("HEAD"), cancellable = true)
	private static void onHandle(ServerPlayerEntity player, NamedScreenHandlerFactory factory, Consumer<PacketByteBuf> extraDataWriter, CallbackInfo ci) {

		BlockEntity blockEntity = null;
		if (factory instanceof SchematicannonBlockEntity) {
			blockEntity = (BlockEntity) factory;
		}

		if (blockEntity == null) return;

		if (!ServersideModdedScreenOpenEvent.EVENT.invoker().onGUIOpen(player, blockEntity)) {
			ci.cancel();
		}
	}
}
