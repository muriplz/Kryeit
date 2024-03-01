package com.kryeit.kryeit;

import com.kryeit.kryeit.event.TrainRelocationEvent;

import com.kryeit.kryeit.listener.OnTrainRelocate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kryeit.kryeit.compat.CompatAddon;
import com.kryeit.kryeit.event.GlueCreateEvent;
import com.kryeit.kryeit.event.GlueKillEvent;
import com.kryeit.kryeit.listener.OnCreateGlue;
import com.kryeit.kryeit.listener.OnKillGlue;

import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
    public static final String MOD_ID = "kryeit";
    public static final Logger LOGGER = LoggerFactory.getLogger("Create: Kryeit");

	@Override
	public void onInitialize() {
		if (CompatAddon.GRIEF_DEFENDER.isLoaded()) {
			GlueCreateEvent.EVENT.register(new OnCreateGlue());
			GlueKillEvent.EVENT.register(new OnKillGlue());
			TrainRelocationEvent.EVENT.register(new OnTrainRelocate());
		}
	}
}
