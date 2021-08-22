package com.ilongross.concurrency.part_2.chapter_7;

import java.util.concurrent.*;

// Отмена задачи с помощь Future

public class CancelTask {

    private static final ExecutorService taskExec = Executors.newFixedThreadPool(10);

    public static void timedRun(Runnable run,
                                long timeout,
                                TimeUnit unit) {

        Future<?> task = taskExec.submit(run);
        try {
            task.get(timeout, unit);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            task.cancel(true);
        }
    }

}
