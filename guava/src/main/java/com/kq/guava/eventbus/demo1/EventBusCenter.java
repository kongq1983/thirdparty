package com.kq.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;

/**
 * @author kq
 * @date 2021-07-23 13:38
 * @since 2020-0630
 */
public class EventBusCenter {

    private static EventBus eventBus = new EventBus();

    private EventBusCenter() {

    }

    public static EventBus getInstance() {
        return eventBus;
    }

    public static void register(Object obj) {
        eventBus.register(obj);
    }

    public static void unregister(Object obj) {
        eventBus.unregister(obj);
    }

    public static void post(Object obj) {
        eventBus.post(obj);
    }

}