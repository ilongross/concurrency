package com.ilongross.concurrency.part_1.chapter_3;

import lombok.Getter;
import lombok.Setter;

public class Animal {
    @Getter
    @Setter
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public boolean isPotentialMate(Animal a) {
        return true;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}
