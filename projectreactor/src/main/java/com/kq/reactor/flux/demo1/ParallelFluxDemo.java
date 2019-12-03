package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * ParallelFluxDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class ParallelFluxDemo {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .parallel(2)
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        System.out.println("-----------------------------------------");


        // The second correctly parallelizes on two threads, as shown in the following outpu
        Flux.range(1, 10)
                .parallel(2)
                .runOn(Schedulers.parallel())
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

    }

}
