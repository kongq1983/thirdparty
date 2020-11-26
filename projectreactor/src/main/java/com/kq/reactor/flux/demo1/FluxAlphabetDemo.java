package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;

/**
 * FluxAlphabetDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class FluxAlphabetDemo {

    public static String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }


    public static void main1(String[] args) {
        Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
                .handle((i, sink) -> {
                    String letter = alphabet(i);
                    if (letter != null)
                        sink.next(letter);
                });

        alphabet.subscribe(System.out::println);
    }

    public static void main(String[] args) {

        Flux.just(-1,10,20,25,30).handle((i,sink)-> {
            String letter = alphabet(i);
            if (letter != null)
                sink.next(letter);
        }).subscribe(System.out::println);

    }
}
