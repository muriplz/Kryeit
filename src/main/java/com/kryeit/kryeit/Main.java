package com.kryeit.kryeit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kryeit.kryeit.commands.KryeitCommands;
import com.kryeit.kryeit.compat.CompatAddon;
import com.kryeit.kryeit.event.ClipboardEditEvent;
import com.kryeit.kryeit.event.ControlsInteractEvent;
import com.kryeit.kryeit.event.FilterInteractEvent;
import com.kryeit.kryeit.event.GlueCreateEvent;
import com.kryeit.kryeit.event.GlueKillEvent;
import com.kryeit.kryeit.event.PotatoCannonShootEvent;
import com.kryeit.kryeit.event.ScheduleEntityInteractEvent;
import com.kryeit.kryeit.event.ToolboxEquipEvent;
import com.kryeit.kryeit.event.ToolboxPickupEvent;
import com.kryeit.kryeit.event.TrainAssembleEvent;
import com.kryeit.kryeit.event.TrainChangeNameEvent;
import com.kryeit.kryeit.event.TrainDisassembleEvent;
import com.kryeit.kryeit.event.TrainRelocationEvent;
import com.kryeit.kryeit.event.TrainStorageInteractEvent;
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

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "kryeit";
    public static final Logger LOGGER = LoggerFactory.getLogger("Create: Kryeit");

    public static TrainTrustManager trainTrustManager;

    public Main(IEventBus modEventBus, ModContainer modContainer) {
        // The claim backend gates everything: without GriefDefender there is no claim data to
        // consult, so the protection listeners simply are not registered and every mixin hook
        // falls through to "allow".
        if (CompatAddon.GRIEF_DEFENDER.isLoaded()) {
            trainTrustManager = new TrainTrustManager();

            GlueCreateEvent.EVENT.register(new OnCreateGlue());
            GlueKillEvent.EVENT.register(new OnKillGlue());
            TrainRelocationEvent.EVENT.register(new OnTrainRelocate());
            ControlsInteractEvent.EVENT.register(new OnControlsInteract());
            ToolboxEquipEvent.EVENT.register(new OnToolboxEquip());
            ClipboardEditEvent.EVENT.register(new OnClipboardEdit());
            ToolboxPickupEvent.EVENT.register(new OnToolboxPickup());
            TrainAssembleEvent.EVENT.register(new OnTrainAssemble());
            TrainDisassembleEvent.EVENT.register(new OnTrainDisassemble());
            TrainChangeNameEvent.EVENT.register(new OnTrainChangeName());
            FilterInteractEvent.EVENT.register(new OnFilterInteract());
            ScheduleEntityInteractEvent.EVENT.register(new OnScheduleEntityInteract());
            TrainStorageInteractEvent.EVENT.register(new OnTrainStorageInteract());
            PotatoCannonShootEvent.EVENT.register(new OnPotatoCannonShoot());

            NeoForge.EVENT_BUS.register(KryeitCommands.class);

            LOGGER.info("Kryeit loaded for NeoForge 1.21.1 (GriefDefender detected)");
        } else {
            LOGGER.info("Kryeit idle: GriefDefender not present, Create claim protection disabled");
        }
    }
}
