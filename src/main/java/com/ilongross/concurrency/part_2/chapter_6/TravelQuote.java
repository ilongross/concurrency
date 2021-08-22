package com.ilongross.concurrency.part_2.chapter_6;

import lombok.Getter;

public class TravelQuote {

    @Getter private String quote;

    public TravelQuote(String quote) {
        this.quote = quote;
    }


}
