package com.kq.reactor.basesubscriber;

import reactor.core.publisher.Flux;

/**
 * SampleSubscriberDemo
 *
 * @author kq
 * @date 2019-09-16
 */
public class SampleSubscriberDemo {

    public static void main(String[] args) {
        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> {System.out.println("Done");},
                s -> s.request(10));
        ints.subscribe(ss);
    }

}
