package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;

/**
 * FluxRequestDemo
 *
 * @author kq
 * @date 2019-12-02
 */
public class FluxRequestDemo {


    public static void main(String[] args) {
//        request1();
        request15();
    }

    /**
     * 打印1 2 3 4 Done
     */
    public static void request1(){
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(10));
    }

    /**
     * 打印1 2 3 4 5 6 7 8 9 10
     * 不会输出Done
     */
    public static void request15(){
        Flux<Integer> ints = Flux.range(1, 15);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(10));
    }

}
