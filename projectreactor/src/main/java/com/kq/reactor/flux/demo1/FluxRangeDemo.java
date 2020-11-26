package com.kq.reactor.flux.demo1;

import reactor.core.publisher.Flux;

/**
 * FluxRangeDemo
 *
 * @author kq
 * @date 2019-12-02
 */
public class FluxRangeDemo {

    public static void main(String[] args) {
        // 打印 5 6 7
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
        numbersFromFiveToSeven.subscribe(System.out::println);
//        rangeError();
        rangeError1();
    }

    public static void rangeError(){
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });

        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error: " + error));
    }

    public static void rangeError1(){

        Flux<Integer> ints = Flux.range(1,10).map(i->{
            return i;
//            if(i!=4) return i;
//            throw new RuntimeException("Got to 4");
        });

        ints.subscribe(System.out::println,e -> e.printStackTrace(),()-> System.out.println("runnable is execute."));

    }

}
