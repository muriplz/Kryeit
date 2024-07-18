package com.kryeit.kryeit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kryeit.kryeit.commands.TrainTrust;
import com.kryeit.kryeit.commands.TrainUntrust;
import com.kryeit.kryeit.compat.CompatAddon;
import com.kryeit.kryeit.event.ClipboardEditEvent;
import com.kryeit.kryeit.event.ControlsInteractEvent;
import com.kryeit.kryeit.event.GlueCreateEvent;
import com.kryeit.kryeit.event.GlueKillEvent;
import com.kryeit.kryeit.event.PotatoCannonShootEvent;
import com.kryeit.kryeit.event.ScheduleEntityInteractEvent;
import com.kryeit.kryeit.event.ToolboxEquipEvent;
import com.kryeit.kryeit.event.ToolboxPickupEvent;
import com.kryeit.kryeit.event.TrainAssemblyModeEvent;
import com.kryeit.kryeit.event.TrainStorageInteractEvent;
import com.kryeit.kryeit.event.TrainRelocationEvent;
import com.kryeit.kryeit.listener.OnClipboardEdit;
import com.kryeit.kryeit.listener.OnControlsInteract;
import com.kryeit.kryeit.listener.OnCreateGlue;
import com.kryeit.kryeit.listener.OnKillGlue;
import com.kryeit.kryeit.listener.OnPotatoCannonShoot;
import com.kryeit.kryeit.listener.OnScheduleEntityInteract;
import com.kryeit.kryeit.listener.OnToolboxEquip;
import com.kryeit.kryeit.listener.OnToolboxPickup;
import com.kryeit.kryeit.listener.OnTrainAssemblyMode;
import com.kryeit.kryeit.listener.OnTrainStorageInteract;
import com.kryeit.kryeit.listener.OnTrainRelocate;
import com.kryeit.kryeit.storage.TrainTrustManager;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class Main implements ModInitializer {
    public static final String MOD_ID = "kryeit";
    public static final Logger LOGGER = LoggerFactory.getLogger("Create: Kryeit");

	public static TrainTrustManager trainTrustManager;
	@Override
	public void onInitialize() {

		if (CompatAddon.GRIEF_DEFENDER.isLoaded()) {
			trainTrustManager = new TrainTrustManager();

			GlueCreateEvent.EVENT.register(new OnCreateGlue());
			GlueKillEvent.EVENT.register(new OnKillGlue());
			TrainRelocationEvent.EVENT.register(new OnTrainRelocate());
			ControlsInteractEvent.EVENT.register(new OnControlsInteract());
			ToolboxEquipEvent.EVENT.register(new OnToolboxEquip());
			ToolboxPickupEvent.EVENT.register(new OnToolboxPickup());
			ClipboardEditEvent.EVENT.register(new OnClipboardEdit());
			TrainAssemblyModeEvent.EVENT.register(new OnTrainAssemblyMode());
			ScheduleEntityInteractEvent.EVENT.register(new OnScheduleEntityInteract());
			TrainStorageInteractEvent.EVENT.register(new OnTrainStorageInteract());

			PotatoCannonShootEvent.EVENT.register(new OnPotatoCannonShoot());

			CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, commandSelection) -> {
				TrainTrust.register(dispatcher);
				TrainUntrust.register(dispatcher);
			});
		}
	}
}
