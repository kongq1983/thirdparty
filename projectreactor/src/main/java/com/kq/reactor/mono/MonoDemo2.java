package com.kq.reactor.mono;

import reactor.core.publisher.Mono;

/**
 * MonoDemo2
 *
 * @author kq
 * @date 2019-12-03
 */
public class MonoDemo2 {

    public static void main(String[] args) {
        String key = "message";
        Mono<String> r = Mono.just("Hello")
                .subscriberContext(ctx -> ctx.put(key, "World"))
                .flatMap( s -> Mono.subscriberContext()
                        .map( ctx -> s + " " + ctx.getOrDefault(key, "Stranger")));

        r.subscribe(System.out::println);

        print();

        print1();

    }

    public static void print(){
        String key = "message";

        Mono<String> r = Mono.subscriberContext()
                .map( ctx -> ctx.put(key, "Hello"))
                .flatMap( ctx -> Mono.subscriberContext())
                .map( ctx -> ctx.getOrDefault(key,"Default"));
        r.subscribe(System.out::println);
    }

    public static void print1(){
        String key = "message";
        Mono<String> r = Mono.just("Hello")
                .flatMap( s -> Mono.subscriberContext()
                        .map( ctx -> s + " " + ctx.get(key)))
                .subscriberContext(ctx -> ctx.put(key, "Reactor"))
                .flatMap( s -> Mono.subscriberContext()
                        .map( ctx -> s + " " + ctx.get(key)))
                .subscriberContext(ctx -> ctx.put(key, "World"));

        r.subscribe(System.out::println);
    }

}
