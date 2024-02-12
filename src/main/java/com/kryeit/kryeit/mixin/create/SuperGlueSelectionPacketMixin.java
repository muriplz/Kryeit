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

import java.util.List;

import com.simibubi.create.content.contraptions.glue.SuperGlueSelectionHelper;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kryeit.kryeit.event.GlueCreateEvent;
import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;
import com.simibubi.create.content.contraptions.glue.SuperGlueSelectionPacket;
import com.simibubi.create.foundation.networking.SimplePacketBase;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SuperGlueSelectionPacket.class)
public class SuperGlueSelectionPacketMixin {

	@Shadow
	private BlockPos from;
	@Shadow
	private BlockPos to;

	@Inject(method = "handle", remap = false, at = @At("HEAD"), cancellable = true)
	public void onActivate(SimplePacketBase.Context context, CallbackInfoReturnable<Boolean> cir){
		ServerPlayer player = context.getSender();
		AABB bb = SuperGlueEntity.span(from, to);

		for (SuperGlueEntity glueEntity : player.level().getEntitiesOfClass(SuperGlueEntity.class, bb)) {
			AABB glueBox = glueEntity.getBoundingBox();

			if (bb.equals(glueBox)) {
				if (!GlueCreateEvent.EVENT.invoker().onCreateGlue(player, glueEntity)) {
					cir.cancel();
				}
				return;
			}
		}
	}
}
