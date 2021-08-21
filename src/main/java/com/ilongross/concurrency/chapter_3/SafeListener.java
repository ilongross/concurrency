package com.ilongross.concurrency.chapter_3;

import java.util.EventListener;


// Использование фабричного метода для предотвращения ускользания ссылки this
// во время конструирования

public class SafeListener {
    private final EventListener listener;

    private SafeListener() {
        listener = new EventListener() {
//            public void onEvent(Event e){
//                System.out.println("Event: " + e);
//            }
            @Override
            public int hashCode() {
                return super.hashCode();
            }

        };
    }

//    public static SafeListener newInstance(EventSource source) {
//        SafeListener safe = new SafeListener();
//        source.registerListener(safe.listener);
//        return safe;
//    }

}
