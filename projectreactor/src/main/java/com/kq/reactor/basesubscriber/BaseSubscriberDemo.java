package com.kq.reactor.basesubscriber;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * BaseSubscriberDemo
 *
 * @author kq
 * @date 2019-09-16
 */
public class BaseSubscriberDemo {

    public static void main(String[] args) {
        Flux.range(1, 10)
            .doOnRequest(r -> System.out.println("request of " + r))
            .subscribe(new BaseSubscriber<Integer>() {

                @Override
                public void hookOnSubscribe(Subscription subscription) {
                    request(1);
                }

                @Override
                public void hookOnNext(Integer integer) {
                    System.out.println("Cancelling after having received " + integer);
                    cancel();
                }
            });
    }

}
