package com.kryeit.kryeit.event.api;

/**
 * Minimal array-backed event, mirroring the shape of Fabric's Event so the listener/mixin
 * call sites (EVENT.register(...), EVENT.invoker().onX(...)) stay unchanged after dropping the
 * Fabric API dependency on NeoForge.
 */
public interface Event<T> {
    T invoker();

    void register(T listener);
}
