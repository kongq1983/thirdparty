package com.kq.reactor.flux.demo1;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

/**
 * FluxBackpressureDemo
 *
 * @author kq
 * @date 2019-12-02
 */
public class FluxBackpressureDemo {

    public static void main(String[] args) {
//        backpresssure();
//        backpresssure1();
        backpresssure2();
    }


    public static void backpresssure() {
        Flux.range(1, 10)
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new BaseSubscriber<Integer>() {

                    @Override
                    public void hookOnSubscribe(Subscription subscription) { // onSubscribe
                        System.out.println("hookOnSubscribe----------------------");
                        request(1);
                    }

                    @Override
                    public void hookOnNext(Integer integer) { // onNext
                        System.out.println("Cancelling after having received " + integer);
//                        cancel();
                        request(1);
                    }
                });
    }

    public static void backpresssure1() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                });
    }

    public static void backpresssure2() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                }, (state) -> System.out.println("state: " + state));

        flux.subscribe(System.out::println);
    }

}
