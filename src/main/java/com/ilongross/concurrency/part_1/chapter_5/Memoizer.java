package com.ilongross.concurrency.part_1.chapter_5;

import org.apache.commons.lang3.concurrent.Computable;

import java.util.concurrent.*;


// Класс Мемоизатор для кэширования объектов и использования объектов из кэша

public class Memoizer<A, V> implements Computable<A, V> {

    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> computable;

    public Memoizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {

        while(true) {
            Future<V> f = cache.get(arg);
            if(f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return computable.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                f = cache.putIfAbsent(arg, ft);
                if(f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


}
