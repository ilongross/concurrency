package com.ilongross.concurrency.part_2.chapter_7;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


// Сокрытие нестандартной отмены в потоке Thread путем переопределения метода interrupt()

public class ReaderThread extends Thread{

    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[100];
            while(true) {
                int count = in.read(buf);
                if(count < 0)
                    break;
                else
                    if(count > 0)
                        processBuffer(buf, count);
            }
        } catch (IOException e) {
            // Позводяет выход потока
            e.printStackTrace();
        }
    }

    private void processBuffer(byte[] buf, int count) {
        // Реализация обработки входящего потока из сокета
    }

}
