package com.ilongross.concurrency.part_2.chapter_7;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Использование волатильного поля для хранения состояния отмены

@ThreadSafe
public class PrimeGenerator implements Runnable, NumberGenerator{

    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<BigInteger>();

    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while(!cancelled) {
            p = p.nextProbablePrime();
            if(Integer.valueOf(p.toString()) > 10000)
//                cancel();
            synchronized (this) {
                primes.add(p);
                System.out.println(p);
            };
        }
        System.out.println(primes.size());
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }

}
