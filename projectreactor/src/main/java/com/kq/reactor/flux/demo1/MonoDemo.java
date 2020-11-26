package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Mono;

/**
 * MonoDemo
 *
 * @author kq
 * @date 2019-12-02
 */
public class MonoDemo {

    public static void main(String[] args) {
        Mono<String> mono = Mono.just("welcome");
        mono.subscribe(System.out::println);

    }

}
