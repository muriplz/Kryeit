package com.kryeit.kryeit.event.api;

import java.lang.reflect.Array;
import java.util.function.Function;

public final class EventFactory {
    private EventFactory() {}

    public static <T> Event<T> createArrayBacked(Class<T> type, Function<T[], T> invokerFactory) {
        return new ArrayBackedEvent<>(type, invokerFactory);
    }

    private static final class ArrayBackedEvent<T> implements Event<T> {
        private final Class<T> type;
        private final Function<T[], T> invokerFactory;
        private T[] listeners;
        private T invoker;

        @SuppressWarnings("unchecked")
        ArrayBackedEvent(Class<T> type, Function<T[], T> invokerFactory) {
            this.type = type;
            this.invokerFactory = invokerFactory;
            this.listeners = (T[]) Array.newInstance(type, 0);
            this.invoker = invokerFactory.apply(listeners);
        }

        @Override
        public T invoker() {
            return invoker;
        }

        @Override
        @SuppressWarnings("unchecked")
        public synchronized void register(T listener) {
            T[] grown = (T[]) Array.newInstance(type, listeners.length + 1);
            System.arraycopy(listeners, 0, grown, 0, listeners.length);
            grown[listeners.length] = listener;
            listeners = grown;
            invoker = invokerFactory.apply(listeners);
        }
    }
}
