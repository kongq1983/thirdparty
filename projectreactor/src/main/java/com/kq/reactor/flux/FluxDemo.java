package com.kq.reactor.flux;

import reactor.core.publisher.Flux;

/**
 * FluxDemo
 *
 * @author kq
 * @date 2019-09-16
 */
public class FluxDemo {

    public static void main(String[] args) {
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3*state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });
    }

}
