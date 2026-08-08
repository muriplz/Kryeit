package com.kryeit.kryeit.compat;

import net.neoforged.fml.ModList;

public enum CompatAddon {
    GRIEF_DEFENDER("griefdefender"),
    LUCKPERMS("luckperms");

    private final String id;

    CompatAddon(String id) {
        this.id = id;
    }

    public boolean isLoaded() {
        return ModList.get() != null && ModList.get().isLoaded(id());
    }

    public String id() {
        return id;
    }
}
