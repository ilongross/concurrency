package com.ilongross.concurrency.part_2.chapter_6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// Веб-сервер использующий пул потоков

public class TaskExecutionWebServer {
    private static final int NTHREADS = 100;
//    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
    private static final ExecutorService exec = Executors.newSingleThreadExecutor();
    private static final int socketNumber = 80;
    private static int counter = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(socketNumber);
        counter = socketNumber;
        while(true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        try {
                            System.out.println(decrementCounter());
                            Thread.sleep(180);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            exec.execute(task);
            exec.shutdown();
            break;
        }
    }

    private static synchronized int decrementCounter() {
        return --counter;
    }
}
