package com.kryeit.kryeit.event;

import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface DeployerInteractionEvent {
    Event<DeployerInteractionEvent> EVENT = EventFactory.createArrayBacked(DeployerInteractionEvent.class, listeners -> (blockEntity) -> {
        for (DeployerInteractionEvent listener : listeners) {
            return listener.onDeployerInteract(blockEntity);
        }
        return false;
    });

    boolean onDeployerInteract(DeployerBlockEntity blockEntity);
}

