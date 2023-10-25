package com.kryeit.kryeit.listener;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.Tristate;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.permission.Context;
import com.griefdefender.api.permission.flag.Flags;
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

public class test implements SchematicannonPlaceEvent {


    @Override
    public boolean onCannonPlace(BlockEntity cannon, BlockPos target) {
		final Claim claim = GriefDefender.getCore().getClaimAt(GriefDefender.getCore().getWorldUniqueId(cannon.getLevel()),
				cannon.getBlockPos().getX(), cannon.getBlockPos().getY(), cannon.getBlockPos().getZ());
		final Set<Context> contexts = new HashSet<>();
		contexts.add(new Context("target", "create:schematicannon"));
		final Tristate result = GriefDefender.getPermissionManager().getActiveFlagPermissionValue(this,
				cannon.getBlockPos().getX(), cannon.getBlockPos().getY(), cannon.getBlockPos().getZ()
				, claim, null, Flags.BLOCK_PLACE,null, cannon, contexts, null, true);
		return result == Tristate.FALSE;
	}
}
