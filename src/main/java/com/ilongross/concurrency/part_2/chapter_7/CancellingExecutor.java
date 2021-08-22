package com.ilongross.concurrency.part_2.chapter_7;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.*;

// Остановка поточной лужбы

@ThreadSafe
public class CancellingExecutor extends ThreadPoolExecutor {

    public CancellingExecutor(int corePoolSize, int maximumPoolSize,
                              long keepAliveTime, TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if(callable instanceof CancelTask)
            return ((CancellableTask<T>)callable).newTask();
        else
            return super.newTaskFor(callable);
    }

}
