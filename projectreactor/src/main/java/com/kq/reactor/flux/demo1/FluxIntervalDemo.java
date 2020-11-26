package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * FluxIntervalDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class FluxIntervalDemo {

    public static void main(String[] args) throws Exception{

//        interval();
        interval1();

    }

    public static void interval() throws Exception{
        // 每1s 打印一次
        Flux<String> flux =
                Flux.interval(Duration.ofMillis(1000))
                        .map(input -> {
                            if (input < 3) return "tick " + input;
                            throw new RuntimeException("boom");
                        })
                        .doOnError(e-> e.printStackTrace())
                        .onErrorReturn("Uh oh");

        flux.subscribe(System.out::println);
        Thread.sleep(5100);
    }

    public static void interval1() throws Exception{

        Flux.interval(Duration.ofMillis(250))
                .map(input -> {
                    if (input < 3) return "tick " + input;
                    throw new RuntimeException("boom");
                })
                .retry(1) // 报错的时候  重试1次
                .elapsed()
                .subscribe(System.out::println, System.err::println);

        Thread.sleep(3100);
//        Thread.sleep(5100);

    }

//    [267,tick 0]
//    [250,tick 1]
//    [256,tick 2]  这个之后会报错，根据retry(1) ，然后重试(从0开始)
//    [498,tick 0]
//    [249,tick 1]
//    [250,tick 2] 由于retry(1) 只获取1次，然后会报错
//    java.lang.RuntimeException: boom

}
