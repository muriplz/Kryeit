package com.kryeit.kryeit.listener;

import java.util.List;

import com.kryeit.kryeit.event.GlueCreateEvent;
import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;

import net.minecraft.server.level.ServerPlayer;

public class OnKillGlue implements GlueCreateEvent {

	@Override
	public boolean onCreateGlue(ServerPlayer player, SuperGlueEntity entity) {

		System.out.println("Glue entities: " + entity);
		System.out.println("Player: " + player);
		return false;
	}
}
