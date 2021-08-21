package com.ilongross.concurrency.part_1.chapter_3;

import net.jcip.annotations.NotThreadSafe;

// Этот класс не рекомендован к использованию
/* Он не является потокобезопасным, тк доступ к полю value осуществляется как из get, так и из set
без синхронизации.
По этой причине он подвержен устаревшим значениям: если один поток вызывает set, то другие потоки,
вызывающие get, могут не увидеть обновления.
 */


@NotThreadSafe
public class MutableInteger {
    private int value;

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
