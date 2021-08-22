package com.ilongross.concurrency.part_2.chapter_7;

import java.math.BigInteger;
import java.util.List;

// Генерирование простых чисел в течении секунды.

public class EverySecondsGenerator {

    public List<BigInteger> everySecondPrimeBigDecimal() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            Thread.sleep(1000);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }

}
