package com.ilongross.concurrency;


import com.ilongross.concurrency.part_2.chapter_6.LifeCycleWebServer;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        LifeCycleWebServer lcws = new LifeCycleWebServer();
        lcws.start();
    }

}

