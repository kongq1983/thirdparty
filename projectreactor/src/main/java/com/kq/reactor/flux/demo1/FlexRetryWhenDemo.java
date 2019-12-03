package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;

/**
 * FlexRetryThenDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class FlexRetryWhenDemo {

    public static void main(String[] args) {

        Flux<String> flux = Flux
                .<String>error(new IllegalArgumentException())
                .doOnError(System.out::println)
                .retryWhen(companion -> companion.take(3));

        flux.subscribe(System.out::println);

    }

}
