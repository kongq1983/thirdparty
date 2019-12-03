package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * FlexNewParallelDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class FlexNewParallelDemo {

    public static void main(String[] args) throws Exception{
        // 4个 ScheduledExecutorService
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .publishOn(s)
                .map(i -> "value " + i);

        Thread thread = new Thread(() -> flux.subscribe(System.out::println));
        thread.start();

        thread.join();
        s.dispose();
    }

}
