package com.ilongross.concurrency.part_2.chapter_7;

import net.jcip.annotations.GuardedBy;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

// Остановка поточной лужбы


public abstract class SocketUsingTask<T> implements CancellableTask<T> {

    @GuardedBy("this") private Socket socket;

    protected synchronized  void setSocket(Socket s) {
        socket = s;
    }

    public synchronized void cancel() {
        try {
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                }finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}
