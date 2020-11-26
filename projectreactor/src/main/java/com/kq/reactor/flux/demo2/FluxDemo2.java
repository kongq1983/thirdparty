package com.kq.reactor.flux.demo2;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * FluxDemo2
 *
 * @author kq
 * @date 2019-09-17
 */
public class FluxDemo2 {

    public static String convert(int i) throws IOException {
        System.out.println("convert i="+i);
        if (i > 3) {
            System.out.println("i>3");
            throw new IOException("boom " + i);
        }
        return "OK " + i;
    }

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

}
