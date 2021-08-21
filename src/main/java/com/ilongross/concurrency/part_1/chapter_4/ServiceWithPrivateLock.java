package com.ilongross.concurrency.part_1.chapter_4;


import lombok.Getter;
import net.jcip.annotations.GuardedBy;

// Защита состояния с помощью приватного замка
/*
    Преимущество приватного замка - он инкапсулирован в класс.
    Этот способ не позволяет клиентскому коду приобрести его и участвовать в политике синхронизации,
    избавляя от необходимости проверки всей программы.
 */

public class ServiceWithPrivateLock<E> {

    private final Object myLock = new Object();

    @GuardedBy("myLock")
    @Getter
    private E e;

    public ServiceWithPrivateLock(E e) {
        this.e = e;
    }

    void showDescription() {
        synchronized (myLock) {
            System.out.println(e);
        }
    }
}
