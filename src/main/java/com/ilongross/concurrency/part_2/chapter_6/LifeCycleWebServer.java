package com.ilongross.concurrency.part_2.chapter_6;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

// Веб-сервер с поддержкой выключения

public class LifeCycleWebServer {

    private final ExecutorService execService = Executors.newFixedThreadPool(10);

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while(!execService.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                execService.execute(new Runnable() {
                    @SneakyThrows
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            if(!handleRequest(i))
                                break;
                            System.out.println(conn);
                            Thread.sleep(140);
                        }
                    }
                });
            } catch (RejectedExecutionException e) {
                e.printStackTrace();
            }
            stop();
        }
    }

    public void stop() {
        execService.shutdown();
    }

    public boolean handleRequest(int stop) {
        if(stop == 20)
            return false;
        return true;
    }

}
