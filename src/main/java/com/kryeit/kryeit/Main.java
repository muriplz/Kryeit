package com.kryeit.kryeit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kryeit.kryeit.event.GlueCreateEvent;
import com.kryeit.kryeit.event.GlueKillEvent;
import com.kryeit.kryeit.listener.OnCreateGlue;
import com.kryeit.kryeit.listener.OnKillGlue;
import com.simibubi.create.foundation.data.CreateRegistrate;

import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
    public static final String MOD_ID = "kryeit";
    public static final Logger LOGGER = LoggerFactory.getLogger("Create: Kryeit");
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }

	@Override
	public void onInitialize() {
		GlueCreateEvent.EVENT.register(new OnCreateGlue());
		GlueKillEvent.EVENT.register(new OnKillGlue());
	}
}
