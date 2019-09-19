package com.kq.reactor.basesubscriber;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 *
 * https://projectreactor.io/docs/core/release/reference/index.html
 * SampleSubscriber
 * @author kq
 * @date 2019-09-16
 */
public class SampleSubscriber<T> extends BaseSubscriber<T> {

    @Override
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    @Override
    public void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }
}