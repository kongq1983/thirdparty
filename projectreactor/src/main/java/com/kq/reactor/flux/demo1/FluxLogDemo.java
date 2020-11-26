package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;

/**
 * FluxLogDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class FluxLogDemo {

    public static void main(String[] args) {
        int takeSize = 3;
        Flux<Integer> flux = Flux.range(1, 10).log().take(takeSize);
        flux.subscribe(System.out::println);
    }

}
