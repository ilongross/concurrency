package com.ilongross.concurrency.chapter_1;

public class Test_1 {

    public static void main(String[] args) {


        ThreadSafeSequenceGenerator tsg = new ThreadSafeSequenceGenerator();
        Thread t1 = tsg;

        for (int i = 0; i < 10; i++) {
            t1.start();
        }

        System.out.println(tsg.getValue());
    }

}
