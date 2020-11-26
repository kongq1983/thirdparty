package com.kq.reactor.flux.demo1;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * FluxExceptionDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class FluxExceptionDemo {

    public static void main(String[] args) {

        Flux<String> converted = Flux
                .range(1, 10)
                .map(i -> {
                    try { return convert(i); }
                    catch (IOException e) { throw Exceptions.propagate(e); }
                });

        converted.subscribe(
                v -> System.out.println("RECEIVED: " + v),
                e -> {
                    if (Exceptions.unwrap(e) instanceof IOException) {
                        System.out.println("Something bad happened with I/O");
                    } else {
                        System.out.println("Something bad happened");
                    }
                }
        );

    }

    public static String convert(int i) throws IOException {
        if (i > 3) {
            throw new IOException("boom " + i);
        }
        return "OK " + i;
    }

}
