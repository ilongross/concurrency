package com.ilongross.concurrency.part_1.chapter_4;

import com.ilongross.concurrency.part_1.chapter_3.Animal;

public class TestCh4 {
    public static void main(String[] args) {
        ServiceWithPrivateLock pl = new ServiceWithPrivateLock(new Animal("Piggy"));

        pl.showDescription();

    }

}
