package com.kryeit.kryeit.listener;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.Tristate;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.TrustType;
import com.griefdefender.api.claim.TrustTypes;
import com.griefdefender.api.permission.Context;
import com.griefdefender.api.permission.flag.Flags;
import com.kryeit.kryeit.event.GlueKillEvent;
import com.kryeit.kryeit.event.SchematicannonPlaceEvent;
import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test implements GlueKillEvent {

	@Override
	public boolean onKillGlue(ServerPlayer player, List<SuperGlueEntity> entities) {
		Tristate tristate = Tristate.TRUE;
		for (SuperGlueEntity glue : entities) {
			for (BlockPos pos : glue.mainSupportingBlockPos.stream().toList()) {
				final Claim claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(glue.level()),
						pos.getX(), pos.getY(), pos.getZ());
				final Set<Context> contexts = new HashSet<>();
				contexts.add(new Context("target", "create:super_glue"));
				final Tristate result = GriefDefender.getPermissionManager().getActiveFlagPermissionValue(this,
						pos.getX(), pos.getY(), pos.getZ()
						, claim, null, Flags.ENTITY_DAMAGE, player, glue, contexts, TrustTypes.BUILDER, true);
				tristate = result;
			}
		}
		return tristate == Tristate.FALSE;
	}
}
