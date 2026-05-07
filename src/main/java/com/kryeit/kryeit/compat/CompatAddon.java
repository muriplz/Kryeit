package com.kryeit.kryeit.compat;

import net.neoforged.fml.ModList;

public enum CompatAddon {
    GRIEF_DEFENDER("griefdefender");

    private final String id;

    CompatAddon(String id) {
        this.id = id;
    }

    public boolean isLoaded() {
        return ModList.get().isLoaded(id());
    }

    public String id() {
        return id;
    }
}
