package com.ilongross.concurrency.chapter_1;

import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;


@ThreadSafe
public class ThreadSafeSequenceGenerator extends Thread{
    private int value = 0;


    public int getNext() {
        return value++;
    }

    public int getValue() {
        return value;
    }

    @Override
    public synchronized void start() {
        getNext();
    }

}
