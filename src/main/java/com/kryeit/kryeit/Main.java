package com.kryeit.kryeit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kryeit.kryeit.commands.TrainTrust;
import com.kryeit.kryeit.commands.TrainUntrust;
import com.kryeit.kryeit.compat.CompatAddon;
import com.kryeit.kryeit.event.ControlsInteractionEvent;
import com.kryeit.kryeit.event.GlueCreateEvent;
import com.kryeit.kryeit.event.GlueKillEvent;
import com.kryeit.kryeit.event.ToolboxDisposeAllEvent;
import com.kryeit.kryeit.event.ToolboxEquipEvent;
import com.kryeit.kryeit.event.TrainRelocationEvent;
import com.kryeit.kryeit.listener.OnControlsInteract;
import com.kryeit.kryeit.listener.OnCreateGlue;
import com.kryeit.kryeit.listener.OnKillGlue;
import com.kryeit.kryeit.listener.OnToolboxDispose;
import com.kryeit.kryeit.listener.OnToolboxEquip;
import com.kryeit.kryeit.listener.OnTrainRelocate;
import com.kryeit.kryeit.storage.TrainTrustManager;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class Main implements ModInitializer {
    public static final String MOD_ID = "kryeit";
    public static final Logger LOGGER = LoggerFactory.getLogger("Create: Kryeit");

	public static TrainTrustManager trainTrustManager = new TrainTrustManager();
	@Override
	public void onInitialize() {

		if (CompatAddon.GRIEF_DEFENDER.isLoaded()) {
			GlueCreateEvent.EVENT.register(new OnCreateGlue());
			GlueKillEvent.EVENT.register(new OnKillGlue());
			TrainRelocationEvent.EVENT.register(new OnTrainRelocate());
			ControlsInteractionEvent.EVENT.register(new OnControlsInteract());
			ToolboxEquipEvent.EVENT.register(new OnToolboxEquip());
			ToolboxDisposeAllEvent.EVENT.register(new OnToolboxDispose());

			CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, commandSelection) -> {
				TrainTrust.register(dispatcher);
				TrainUntrust.register(dispatcher);
			});
		}
	}
}
