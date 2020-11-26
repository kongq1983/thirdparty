package com.kq.demo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Consumer;

/**
 * FluxDemo
 *
 * @author kq
 * @date 2019-12-03
 */
public class FluxDemo {

    public static void main(String[] args) {
        Flux.just("Ben", "Michael", "Mark").subscribe(new Subscriber<String>() {
            public void onSubscribe(Subscription s) {
                s.request(3);
            }

            public void onNext(String s) {
                System.out.println("Hello " + s + "!");
            }

            public void onError(Throwable t) {

            }

            public void onComplete() {
                System.out.println("Completed");
            }
        });

        System.out.println("-------------------------------");
        print();
        System.out.println("-------------------------------");
        print2();
        System.out.println("-------------------------------");
        print3();
        System.out.println("-------------------------------");
        print4();
        System.out.println("-------------------------------");
        printLast();
        System.out.println("-------------------------------");
        collectList();
    }

    public static void print(){
        Flux.just("Ben", "Michael", "Mark").doOnNext(new Consumer<String>() {
            public void accept(String s) {
                System.out.println("Hello " + s + "!");
            }
        }).doOnComplete(new Runnable() {
            public void run() {
                System.out.println("Completed");
            }
        }).subscribe();
    }

    public static void print2(){
        Flux.just("Ben", "Michael", "Mark")
                .doOnNext(s -> System.out.println("Hello " + s + "!"))
                .doOnComplete(() -> System.out.println("Completed"))
                .subscribe();
    }

    public static void print3(){
        Flux.just("Ben", "Michael", "Mark") //
                .doOnNext(s -> System.out.println("Hello " + s + "!"))
                .doOnComplete(() -> System.out.println("Completed"))
                .take(2)
                .subscribe();
    }

    public static void print4(){
        Flux.just("Ben", "Michael", "Mark").subscribe(new Subscriber<String>() {
            public void onSubscribe(Subscription s) {
                s.request(3);
            }

            public void onNext(String s) {
                System.out.println("Hello " + s + "!");
            }

            public void onError(Throwable t) {
                System.out.println("onError: " + t);
            }

            public void onComplete() {
                System.out.println("Completed");
            }
        });
    }

    public static void printLast(){
        String last = Flux.just("Ben", "Michael", "Mark").last().block();
        System.out.println("printLast:"+last);
    }

    public static void collectList(){
        List<String> list = Flux.just("Ben", "Michael", "Mark").collectList().block();
        System.out.println("collectList:"+list);
    }

}
