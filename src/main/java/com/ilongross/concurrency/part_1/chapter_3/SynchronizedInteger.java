package com.ilongross.concurrency.part_1.chapter_3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

// Потокобезопасный изменяемый держатель целого числа
// Рекомендован к использованию
// Оба метода get и set должны быть с synchronized

@ThreadSafe
public class SynchronizedInteger {

    @GuardedBy("this")
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
