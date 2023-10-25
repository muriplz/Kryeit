package com.kryeit.kryeit;

import com.kryeit.kryeit.event.ContraptionMoveEvent;
import com.kryeit.kryeit.event.GlueKillEvent;
import com.kryeit.kryeit.event.SchematicannonPlaceEvent;

import com.kryeit.kryeit.listener.t;
import com.kryeit.kryeit.listener.test;
import com.simibubi.create.foundation.data.CreateRegistrate;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final String MOD_ID = "kryeit";
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }

	@Override
	public void onInitialize() {
		GlueKillEvent.EVENT.register(new test());
		ContraptionMoveEvent.EVENT.register(new t());
	}
}
