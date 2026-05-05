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

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.compat.GriefDefenderImpl;
import com.kryeit.kryeit.event.GlueKillEvent;
import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;
import com.simibubi.create.content.contraptions.glue.SuperGlueRemovalPacket;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.common.NeoForge;

@Mixin(value = SuperGlueRemovalPacket.class, remap = false)
public class SuperGlueRemovalPacketMixin {
	@Final
	@Shadow
	private int entityId;

	@Inject(method = "handle", remap = false, at = @At("HEAD"), cancellable = true)
	private void onHandle(ServerPlayer player, CallbackInfo ci) {
		Entity glueEntity = player.level().getEntity(entityId);
		if (glueEntity instanceof SuperGlueEntity superGlue) {
			GlueKillEvent event = NeoForge.EVENT_BUS.post(new GlueKillEvent(player, GriefDefenderImpl.getBlockPositionsInAABB(superGlue.getBoundingBox())));
			if (event.isCanceled()) ci.cancel();
		}
	}
}
