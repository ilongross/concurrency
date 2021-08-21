package com.ilongross.concurrency.chapter_3;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;


//  Ограничение локальных примитивных и ссылочных переменных одним потоком

public class RestrictingLocalVariables {

    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;

        // ограничение ускользания animals
        animals = new TreeSet<Animal>();
        animals.addAll(candidates);
        for (Animal a : animals) {
            if(candidate == null || !candidate.isPotentialMate(a))
                candidate = a;
            else {
                ++numPairs;
                candidate = null;
            }
        }
        return  numPairs;
    }

}
