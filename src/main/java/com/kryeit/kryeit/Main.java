package com.kryeit.kryeit;

import org.slf4j.Logger;

import com.kryeit.kryeit.commands.TrainTrust;
import com.kryeit.kryeit.commands.TrainUntrust;
import com.kryeit.kryeit.compat.CompatAddon;
import com.kryeit.kryeit.listener.OnClipboardEdit;
import com.kryeit.kryeit.listener.OnControlsInteract;
import com.kryeit.kryeit.listener.OnCreateGlue;
import com.kryeit.kryeit.listener.OnFilterInteract;
import com.kryeit.kryeit.listener.OnKillGlue;
import com.kryeit.kryeit.listener.OnPotatoCannonShoot;
import com.kryeit.kryeit.listener.OnScheduleEntityInteract;
import com.kryeit.kryeit.listener.OnToolboxEquip;
import com.kryeit.kryeit.listener.OnToolboxPickup;
import com.kryeit.kryeit.listener.OnTrainAssemble;
import com.kryeit.kryeit.listener.OnTrainChangeName;
import com.kryeit.kryeit.listener.OnTrainDisassemble;
import com.kryeit.kryeit.listener.OnTrainRelocate;
import com.kryeit.kryeit.listener.OnTrainStorageInteract;
import com.kryeit.kryeit.storage.TrainTrustManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.logging.LogUtils;

import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;


@Mod(Main.MOD_ID)
public class Main {
	private static final Logger LOGGER = LogUtils.getLogger();
	public static final String MOD_ID = "kryeit";

	public static TrainTrustManager trainTrustManager;

	public Main() {
		NeoForge.EVENT_BUS.register(this);

		if (!CompatAddon.GRIEF_DEFENDER.isLoaded()) return;
		LOGGER.info("Enabling Kryeit GriefDefender support");

		trainTrustManager = new TrainTrustManager();

		NeoForge.EVENT_BUS.register(OnCreateGlue.class);
		NeoForge.EVENT_BUS.register(OnKillGlue.class);
		NeoForge.EVENT_BUS.register(OnTrainRelocate.class);
		NeoForge.EVENT_BUS.register(OnControlsInteract.class);
		NeoForge.EVENT_BUS.register(OnToolboxEquip.class);
		NeoForge.EVENT_BUS.register(OnClipboardEdit.class);
		NeoForge.EVENT_BUS.register(OnToolboxPickup.class);
		NeoForge.EVENT_BUS.register(OnTrainAssemble.class);
		NeoForge.EVENT_BUS.register(OnTrainDisassemble.class);
		NeoForge.EVENT_BUS.register(OnTrainChangeName.class);
		NeoForge.EVENT_BUS.register(OnFilterInteract.class);
		NeoForge.EVENT_BUS.register(OnScheduleEntityInteract.class);
		NeoForge.EVENT_BUS.register(OnTrainStorageInteract.class);
		NeoForge.EVENT_BUS.register(OnPotatoCannonShoot.class);
	}

	@SubscribeEvent
	public void onCommandRegistration(RegisterCommandsEvent event) {
		CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

		TrainTrust.register(dispatcher);
		TrainUntrust.register(dispatcher);
	}
}
