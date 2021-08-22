package com.ilongross.concurrency.part_2.chapter_6;

import lombok.Getter;

public class TravelInfo {

    @Getter private String info;

    public TravelInfo(String info) {
        this.info = info;
    }
}
