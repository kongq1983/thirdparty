package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * FluxSubscribeOnDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class FluxSubscribeOnDemo {

    public static void main(String[] args) {

        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .subscribeOn(s)
                .map(i -> "value " + i);

        new Thread(() -> flux.subscribe(System.out::println)).start();
    }

}
