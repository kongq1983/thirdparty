package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;

/**
 * FluxJustDemo
 *
 * @author kq
 * @date 2019-12-02
 */
public class FluxJustDemo {

    public static void main(String[] args) {
        Flux<String> flux = Flux.just("1","2","3");
        flux.subscribe(System.out::println);

    }

}
