package com.kq.rxjava3.demo;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * HelloWorld
 *
 * @author kq
 * @date 2019-12-02
 */
public class HelloWorld {

    public static void main(String[] args) {
        // java8以下
        Flowable.just("before java8 , Hello world")
                .subscribe(new Consumer<String>() {
                    @Override public void accept(String s) {
                        System.out.println(s);
                    }
                });


        //java8
        Flowable.just("java8 , Hello world").subscribe(System.out::println);
    }

}
