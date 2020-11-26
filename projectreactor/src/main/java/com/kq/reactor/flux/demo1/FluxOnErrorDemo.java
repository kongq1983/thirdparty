package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;

/**
 * FluxOnErrorDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class FluxOnErrorDemo {

    public static void main(String[] args) {
        Flux.just(1, 2, 0)
                .map(i -> "100 / " + i + " = " + (100 / i)) //this triggers an error with 0
                .onErrorReturn("Divided by zero :(").subscribe(System.out::println);

        System.out.println("------------------------------------------------------");

        onError();

        System.out.println("------------------------------------------------------");

        Flux.just(10)
                .map(v-> doSomethingDangerous(v))
                .onErrorReturn(-1).subscribe(System.out::println); //print -1

    }

    public static void onError(){
        Flux<String> s = Flux.range(1, 10)
                .map(v -> doSomethingDangerous(v))
                .map(v -> doSecondTransform(v));

        s.subscribe(value -> System.out.println("RECEIVED " + value),
                error -> System.err.println("CAUGHT " + error)
        );
    }

    public static int doSomethingDangerous(int num) {
        return num % (num-10);
    }

    public static String doSecondTransform(int num) {
        return "index="+num;
    }

}
