package com.ilongross.concurrency;


import com.ilongross.concurrency.part_2.chapter_7.EverySecondsGenerator;
import com.ilongross.concurrency.part_2.chapter_7.PrimeGenerator;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        EverySecondsGenerator esg = new EverySecondsGenerator();
        try {
            esg.everySecondPrimeBigDecimal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}

