package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;

import java.util.Arrays;

/**
 * FluxFromIterableDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class FluxFromIterableDemo {

    public static void main(String[] args) {
        Flux<String> source = Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                .map(String::toUpperCase);

        source.subscribe(d -> System.out.println("Subscriber 1: "+d));
        source.subscribe(d -> System.out.println("Subscriber 2: "+d));
    }

}
