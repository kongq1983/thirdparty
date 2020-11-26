package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;

/**
 * FlexGenerateDemo
 *
 * @author kq
 * @date 2019-12-02
 */
public class FlexGenerateDemo {

    public static void main(String[] args) {
        generate();
    }

    public static void generate(){
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3*state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });

        flux.subscribe(System.out::println);
    }

}
