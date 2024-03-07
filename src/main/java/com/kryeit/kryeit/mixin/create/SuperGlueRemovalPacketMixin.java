/*
 * Open Parties and Claims Create Support - adds Create mod support to OPAC
 * Copyright (C) 2023-2023, Xaero <xaero1996@gmail.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of version 3 of the GNU Lesser General Public License
 * (LGPL-3.0-only) as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received copies of the GNU Lesser General Public License
 * and the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package com.kryeit.kryeit.mixin.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.compat.GriefDefenderImpl;
import com.kryeit.kryeit.event.GlueKillEvent;
import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;
import com.simibubi.create.content.contraptions.glue.SuperGlueRemovalPacket;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(value = SuperGlueRemovalPacket.class, remap = false)
public class SuperGlueRemovalPacketMixin {

	@Shadow
	private int entityId;

	@Inject(method = "lambda$handle$0", remap = false, at = @At("HEAD"), cancellable = true)
	private void onHandle(SuperGlueRemovalPacket.Context context, CallbackInfo ci) {
		ServerPlayerEntity player = context.getSender();
		if (player != null) {
			Entity entity = player.getWorld().getEntityById(entityId);
			if (entity instanceof SuperGlueEntity superGlue) {
				if (!GlueKillEvent.EVENT.invoker().onKillGlue(player, GriefDefenderImpl.getBlockPositionsInAABB(superGlue.getBoundingBox()))) {
					ci.cancel();
				}
			}
		}
	}
}
