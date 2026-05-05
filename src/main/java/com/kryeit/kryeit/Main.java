package com.kryeit.kryeit;

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

import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;


@Mod(Main.MOD_ID)
public class Main {
	public static final String MOD_ID = "kryeit";

	public static TrainTrustManager trainTrustManager;
	public static IEventBus MOD_BUS;

	public Main(IEventBus modBus) {
		Main.MOD_BUS = modBus;
		modBus.register(this);
		if (!CompatAddon.GRIEF_DEFENDER.isLoaded()) return;

		trainTrustManager = new TrainTrustManager();

		modBus.register(OnCreateGlue.class);
		modBus.register(OnKillGlue.class);
		modBus.register(OnTrainRelocate.class);
		modBus.register(OnControlsInteract.class);
		modBus.register(OnToolboxEquip.class);
		modBus.register(OnClipboardEdit.class);
		modBus.register(OnToolboxPickup.class);
		modBus.register(OnTrainAssemble.class);
		modBus.register(OnTrainDisassemble.class);
		modBus.register(OnTrainChangeName.class);
		modBus.register(OnFilterInteract.class);
		modBus.register(OnScheduleEntityInteract.class);
		modBus.register(OnTrainStorageInteract.class);
		modBus.register(OnPotatoCannonShoot.class);
	}

	@SubscribeEvent
	public void onCommandRegistration(RegisterCommandsEvent event) {
		CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

		TrainTrust.register(dispatcher);
		TrainUntrust.register(dispatcher);
	}
}
