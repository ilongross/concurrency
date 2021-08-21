package com.ilongross.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    public static void main(String[] args) {

        List<EntityThread> threadList = new ArrayList<>();

        Entity e = new Entity(4, 20);

        EntityThread et1 = new EntityThread("ONE", e);
        EntityThread et2 = new EntityThread("TWO", e);
        EntityThread et3 = new EntityThread("THREE", e);

        threadList.add(et1);
        threadList.add(et2);
        threadList.add(et3);

        ExecutorService service = Executors.newSingleThreadExecutor();

        for (EntityThread et : threadList) {
            service.submit(et);
        }
        service.shutdown();


    }

}

class EntityThread implements Runnable {

    Entity entity;
    Thread t;

    public EntityThread (String threadName, Entity entity) {
        this.entity = entity;
        t = new Thread(this, threadName);
    }

    @Override
    public void run() {
        show();
    }

    private void show() {
        System.out.print(t.getName() + " - START: ");
        entity.showNumbers();
        System.out.println(" end...");
    }

}

class Entity {

    private int low;
    private int high;

    public Entity(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public void showNumbers() {
        for (int i = low; i <= high; i++) {
            System.out.print(i + " ");
        }
    }

}
