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

package com.kryeit.kryeit.mixin.create.testedCancellable;

import com.kryeit.event.GlueCreateEvent;
import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;
import com.simibubi.create.content.contraptions.glue.SuperGlueSelectionPacket;
import com.simibubi.create.foundation.networking.SimplePacketBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SuperGlueSelectionPacket.class)
public class MixinSuperGlueSelectionPacket {

	@Shadow
	private BlockPos from;
	@Shadow
	private BlockPos to;

	@Inject(method = "lambda$handle$0", remap = false, at = @At("HEAD"), cancellable = true)
	public void onActivate(SimplePacketBase.Context ctx, CallbackInfo ci){
		ServerPlayer player = ctx.getSender();
		List<SuperGlueEntity> entities = SuperGlueEntity.collectCropped(ctx.getSender().level(),
				AABB.of(BoundingBox.fromCorners(new Vec3i(from.getX(), from.getY(), from.getZ()),
						new Vec3i(to.getX(), to.getY(), to.getZ())))
		);

		if (GlueCreateEvent.EVENT.invoker().onCreateGlue(player, entities)) {
			ci.cancel();
		}
	}
}
