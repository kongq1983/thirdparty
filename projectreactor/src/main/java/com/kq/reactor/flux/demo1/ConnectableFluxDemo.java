package com.kq.reactor.flux.demo1;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

/**
 * ConnectableFluxDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class ConnectableFluxDemo {

    public static void main(String[] args) throws Exception{

        Flux<Integer> source = Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("subscribed to source"));

        ConnectableFlux<Integer> co = source.publish();

        co.subscribe(System.out::println, e -> {}, () -> {});
        co.subscribe(System.out::println, e -> {}, () -> {});

        System.out.println("done subscribing");
        Thread.sleep(500);
        System.out.println("will now connect");

        co.connect();
    }

}
