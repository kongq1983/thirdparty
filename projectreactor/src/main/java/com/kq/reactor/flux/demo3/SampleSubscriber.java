package com.kq.reactor.flux.demo3;

/**
 * SampleSubscriber
 *
 * @author kq
 * @date 2019-12-02
 */

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

public class SampleSubscriber<T> extends BaseSubscriber<T> {

    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("----------Subscribed------------");
        request(1);
    }

    public void hookOnNext(T value) {
        System.out.println("value="+value);
        request(1);
    }
}
