package com.ilongross.concurrency.part_1.chapter_5;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

public class TestCh5 {

    private static class AnyEntity implements Runnable {
        @Getter private int bound;
        @Getter private String name;

        public AnyEntity(String name, int bound) {
            this.name = name;
            this.bound = bound;
        }

        private void cycle() {
            System.out.print(name + ": ");
            for (int i = 0; i < bound; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        @Override
        public void run() {
            cycle();
        }

    }

    private class AnyEntityThread {
        public Thread t;
        public AnyEntityThread(AnyEntity e) {
            t = new Thread(e);
        }
    }

    public static AnyEntity getAnyEntity(String name, int bound) {
        return new AnyEntity(name, bound);
    }



    public static void main(String[] args) throws InterruptedException, ExecutionException {
        TestHarness th = new TestHarness();
        ExecutorService execService = Executors.newSingleThreadExecutor();

        AnyEntity ay = getAnyEntity("ONE", 10);
        AnyEntity ay1 = getAnyEntity("TWO", 10);
        AnyEntity ay2 = getAnyEntity("THREE", 10);

            execService.execute(ay);
            execService.execute(ay1);
            execService.execute(ay2);
            execService.shutdown();

//        System.out.println(th.timeTasks(1000, ay));

    }
}
