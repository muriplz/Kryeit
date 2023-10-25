package com.kryeit.kryeit.listener;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.Tristate;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.TrustTypes;
import com.griefdefender.api.permission.Context;
import com.griefdefender.api.permission.flag.Flags;
import com.kryeit.kryeit.event.ContraptionMoveEvent;
import com.simibubi.create.content.contraptions.Contraption;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.Set;

public class t implements ContraptionMoveEvent {
	@Override
	public boolean onContraptionMove(Level level, Contraption contraption) {
		Tristate tristate = Tristate.TRUE;
		if (contraption == null) return false;
		for (BlockPos pos : contraption.getBlocks().keySet()) {

			final Claim claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(level),
					pos.getX(), pos.getY(), pos.getZ());
			final Set<Context> contexts = new HashSet<>();
			final Tristate result = GriefDefender.getPermissionManager().getActiveFlagPermissionValue(this,
					pos.getX(), pos.getY(), pos.getZ()
					, claim, null, Flags.ENTER_CLAIM, null, contraption, contexts, TrustTypes.BUILDER, true);
			tristate = result;
		}
		return tristate == Tristate.FALSE;
	}
}
