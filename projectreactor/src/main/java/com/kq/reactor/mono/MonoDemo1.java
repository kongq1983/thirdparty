package com.kq.reactor.mono;

import reactor.core.publisher.Mono;

/**
 * MonoDemo1
 *
 * @author kq
 * @date 2019-12-03
 */
public class MonoDemo1 {

    public static void main(String[] args) throws Exception{
        final Mono<String> mono = Mono.just("hello ");

        Thread t = new Thread(() -> mono
                .map(msg -> msg + "thread ")
                .subscribe(v ->
                        System.out.println(v + Thread.currentThread().getName())
                )
        );
        t.start();
        t.join();
    }

}
