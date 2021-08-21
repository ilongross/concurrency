package com.ilongross.concurrency.chapter_4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

// Простой потокобезопасный счетчик с использованием Java-паттерна "Монитор"

@ThreadSafe
public final class CounterSimpleThreadSafe {

    @GuardedBy("this")
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if(value == Long.MAX_VALUE)
            throw new IllegalStateException("counter overflow");
        return  ++value;
    }

}
