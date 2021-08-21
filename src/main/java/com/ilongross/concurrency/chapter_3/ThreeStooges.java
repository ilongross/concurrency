package com.ilongross.concurrency.chapter_3;


import net.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

// Немутируемый класс, построенный из мутируемых бызовых объектов

@Immutable
public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<String>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }

}
