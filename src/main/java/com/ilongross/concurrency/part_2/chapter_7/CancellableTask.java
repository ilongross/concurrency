package com.ilongross.concurrency.part_2.chapter_7;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

// Остановка поточной лужбы


public interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}
