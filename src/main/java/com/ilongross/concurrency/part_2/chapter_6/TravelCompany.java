package com.ilongross.concurrency.part_2.chapter_6;

import lombok.Getter;

public class TravelCompany {

    @Getter private String name;
    private TravelQuote quote;

    public TravelCompany(String name) {
        this.name = name;
        this.quote = new TravelQuote("gold");
    }

    public TravelQuote solicitQuote(TravelInfo info) {
        if(info.getInfo().equals(quote.getQuote()))
            return quote;
        else
            return new TravelQuote("false");
    }

}
