package com.kryeit.kryeit.listener;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import com.kryeit.kryeit.event.GlueKillEvent;
import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Optional;

public class OnKillGlue implements GlueKillEvent {

	@Override
	public boolean onKillGlue(ServerPlayer player, List<SuperGlueEntity> entities) {

		System.out.println("Glue entities: " + entities);
		Claim claim;
		for (SuperGlueEntity glue : entities) {
			AABB box = glue.getBoundingBox();

			BlockPos pos;
			for (int x = (int) box.minX; x <= box.maxX; x++) {
				for (int y = (int) box.minY; y <= box.maxY; y++) {
					for (int z = (int) box.minZ; z <= box.maxZ; z++) {
						pos = new BlockPos(x, y, z);
						claim = GriefDefender.getCore().getClaimAt(pos);

						System.out.println("Glue claim: " + claim);

						if (claim != null && !claim.canBreak(player, pos, GriefDefender.getCore().getUser(player.getUUID()))) {
							System.out.println("Glue killed in claim, stopping");
							return false;
						}
					}
				}
			}

		}
		return true;
	}
}
