package com.ilongross.concurrency.chapter_3;

// Такой способ не рекомендуется использовать
// из-за многопоточности ready может быть выполнена раньшею и результат number будет равен 0;

public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {

        @Override
        public void run() {
            while(!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {

            new ReaderThread().start();
            number = 42;
            ready = true;

    }


}
