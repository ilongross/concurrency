package com.ilongross.concurrency.part_2.chapter_7;

import net.jcip.annotations.GuardedBy;

import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


// Журналирование с надежной отменой потоков для записи

public class LogService {

    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    @GuardedBy("this") private boolean isShutdown;
    @GuardedBy("this") private int reservations;

    public LogService(PrintWriter writer) {
        this.queue = new ArrayBlockingQueue<String>(10);
        this.loggerThread = new LoggerThread();
        this.writer = writer;
        this.isShutdown = false;
        this.reservations = 0;
    }

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if(isShutdown)
                throw  new IllegalStateException("Programm is shutdown");
            ++reservations;
        }
        queue.put(msg);
    }


    private class LoggerThread extends Thread{

        @Override
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (this) {
                            if (isInterrupted() && reservations == 0)
                                break;
                        }
                        String msg = queue.take();
                        synchronized (this) {
                            --reservations;
                        }
                        writer.println(msg);
                    } catch (Exception e) {
                        // Повторная попытка
                    }
                }
            } finally {
                writer.close();
            }
        }
    }

}


