package com.kq.reactor.flux.demo1;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;

/**
 * FluxZipWithDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class FluxZipWithDemo {

    public static void main(String[] args) throws Exception{
        Flux<String> flux =
                Flux.<String>error(new IllegalArgumentException())
                        .retryWhen(companion -> companion
                                .zipWith(Flux.range(1, 4),
                                        (error, index) -> {
                                            if (index < 4) return index;
                                            else throw Exceptions.propagate(error);
                                        })
                        );

        flux.subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(5);
    }

}
