package com.kq.rxjava3.demo;

import io.reactivex.rxjava3.core.Observable;

/**
 * ObservableDemo
 *
 * @author kq
 * @date 2019-12-02
 */
public class ObservableDemo {

    public static void main(String[] args) {
        Observable.create(emitter -> {
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                if (time % 2 != 0) {
                    emitter.onError(new IllegalStateException("Odd millisecond! time="+time));
                    break;
                }
            }
        }).subscribe(System.out::println, Throwable::printStackTrace);
    }

}
